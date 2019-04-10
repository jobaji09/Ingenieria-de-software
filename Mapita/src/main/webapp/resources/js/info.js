/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$('document').ready(function(){
    console.log("Cargado");
    var sec = $('#micuenta');
    sec.click(function(){
        PF('sidebar1').show();
//        console.log("Entra");
    });
    $('#sidebar').mouseleave(function(){
        PF('sidebar1').hide();
    });
});