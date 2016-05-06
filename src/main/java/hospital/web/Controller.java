/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.web;

import hospital.data.Bean;
import hospital.util.Util;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author yerzhan
 * @param <T>
 */
public abstract class Controller<T> implements Serializable {

    protected List<T> items;

    private T selected;

    private boolean isNew = false;

    public void create() {
        selected = createEntity();
        isNew = true;
    }

    public void save() {
        try {
            getBean().edit(selected);
            if (isNew) {
                selected = null;
                isNew = false;
                items = null;
            }
        } catch (Exception ex) {
            Util.addError(ex.getCause());
        }
    }

    public void cancel() {
        if (isNew) {
            selected = null;
            isNew = false;
        }
    }

    public void delete() {
        getBean().delete(selected);
        selected = null;
        isNew = false;
        items = null;
    }

    public void refresh() {
        getBean().refresh();
        selected = null;
        isNew = false;
        items = null;
    }

    protected abstract Bean getBean();

    protected abstract T createEntity();

    public List<T> getItems() {
        if (items == null) {
            items = getBean().findAll();
        }
        return items;
    }

    public T getSelected() {
        return selected;
    }

    public void setSelected(T selected) {
        this.selected = selected;
        isNew = false;
    }
}
