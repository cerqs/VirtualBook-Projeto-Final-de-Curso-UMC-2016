$.validator.addMethod("equal", function (value, element, param) {
    return value === param;
});

$().ready(function () {
    var validator = $("#formAvaliacao").bind("invalid-form.validate", function () {
        $("#msg").html("VOCE PRECISA ADICIONAR UM COMENTARIO" + validator.numberOfInvalids() + " erro(s)");

    }).validate(
            {
                errorElement: "el",
                errorPlacement: function (error, element) {
                    element.parent("td").next("td").html(error);
                },
                success: function (label) {
                    label.text("").removeClass("error").addClass("ok");
                },
                submitHandler: function (form) {
                    form.submit();
                },
                rules: {
                    txt_comentario: {
                        required: true                        
                    },
              
                },
                messages: {
                    txt_comentario: {
                        required: "Esse campo nao pode ser vazio"              
                    }
                }
            }
    )
});