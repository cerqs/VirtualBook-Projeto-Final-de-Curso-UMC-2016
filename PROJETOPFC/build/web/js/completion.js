/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    $(function () {

        $("#search").autocomplete({//de qual id vou pegar os valores de pesquisa
            appendTo: "#retornoAjax", //retorna os valores em forma de lista
            source: function (request, response) {
                $.ajax({
                    url: "ControleProduto?acao=ajaxConsulta", //url com acao
                    type: "GET",
                    data: {
                        term: request.term		//parametro que vai passar para controle
                    },
                    dataType: "json", //tipo Json
                    success: function (data) {
                        response($.map(data, function (item){
                            return{
                                label: item.titulo
                            }
                            
                        }));			//retorno ajax
                    }
                });
            }
        });
    });
});