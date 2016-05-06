function save(args, compId, dialog) {
    if (args.validationFailed) {
        jQuery('#' + compId + '\\:' + dialog).effect('shake', {times: 3}, 500);
    } else {
        PF(dialog).hide();
    }
}
