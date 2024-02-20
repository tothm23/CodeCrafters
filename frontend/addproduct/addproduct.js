$('#imageInput').change(function(){
    var frm = new FormData();
    frm.append('imageInput', input.files[0]);
    $.ajax({
        method: 'POST',
        address: 'url/to/save/image',
        data: frm,
        contentType: false,
        processData: false,
        cache: false
    });
});
