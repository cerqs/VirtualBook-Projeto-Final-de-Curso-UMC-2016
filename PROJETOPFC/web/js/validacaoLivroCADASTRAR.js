/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function alteraPonto(valorInput) {
    $(valorInput).val(valorInput.val().replace(",", "."));
}



$.validator.addMethod("equal", function (value, element, param) {
    return value === param;
});

$().ready(function () {
    var validator = $("#formCadastrarLivro").bind("invalid-form.validate", function () {
        $("#msg").html("OPS! Este formulario tem " + validator.numberOfInvalids() + " erro(s)");

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
                    txtID: {
                        required: true,
                        number: true
                    },
                    txtExcluir: {
                        required: true,
                        number: true
                    },
                    txtIds: {
                        required: true,
                        number: true
                    },
                    txtTitulo: {
                        required: true
                    },
                    txtAutor: {
                        required: true
                    },
                    txtPreco: {
                        required: true,
                        number: true

                    },
                    txtIsbn: {
                        required: true                        
                    },
                    txtLancamento: {
                        required: true,
                        date: true
                    },
                    txtSinopse: {
                        required: true
                    },
                    txtDescricao: {
                        required: true
                    }

                },
                messages: {
                    txtID: {
                        required: "Esse campo nao pode ser vazio",
                        number: "Este campo e numerico"

                    },
                    txtExcluir: {
                        required: "Esse campo nao pode ser vazio",
                        number: "Este campo e numerico"

                    },
                    txtIds: {
                        required: "Esse campo nao pode ser vazio",
                        number: "Este campo e numerico"

                    },
                    txtTitulo: {
                        required: "Esse campo nao pode ser vazio"

                    },
                    txtAutor: {
                        required: "Esse campo nao pode ser vazio"

                    },
                    txtPreco: {
                        required: "Esse campo nao pode ser vazio",
                        number: "Este campo e numerico"
                    },
                    txtLancamento: {
                        required: "Esse campo nao pode ser vazio",
                        date: "Este campo e uma data"
                    },
                    txtIsbn: {
                        required: "Esse campo nao pode ser vazio"                        

                    },
                    txtSinopse: {
                        required: "Esse campo nao pode ser vazio"

                    },
                    txtDescricao: {
                        required: "Esse campo nao pode ser vazio"
                    }
                }
            }
    )
});



function AparecerTitulo() {

    document.getElementById('ajudaTitulo').style.display = 'block';


}

function SumirTitulo() {

    document.getElementById('ajudaTitulo').style.display = 'none';

}


function AparecerAutor() {

    document.getElementById('ajudaAutor').style.display = 'block';


}

function SumirAutor() {

    document.getElementById('ajudaAutor').style.display = 'none';

}


function AparecerPreco() {

    document.getElementById('ajudaPreco').style.display = 'block';


}

function SumirPreco() {

    document.getElementById('ajudaPreco').style.display = 'none';


}

function AparecerGenero() {

    document.getElementById('ajudaGenero').style.display = 'block';


}

function SumirGenero() {

    document.getElementById('ajudaGenero').style.display = 'none';

}

function AparecerLancamento() {

    document.getElementById('ajudaLancamento').style.display = 'block';


}

function SumirLancamento() {

    document.getElementById('ajudaLancamento').style.display = 'none';

}


function AparecerIsbn() {

    document.getElementById('ajudaIsbn').style.display = 'block';


}

function SumirIsbn() {

    document.getElementById('ajudaIsbn').style.display = 'none';

}


function AparecerSinopse() {

    document.getElementById('ajudaSinopse').style.display = 'block';


}

function SumirSinopse() {

    document.getElementById('ajudaSinopse').style.display = 'none';

}



