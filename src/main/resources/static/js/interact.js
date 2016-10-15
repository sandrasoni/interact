function addRow(act){  
    var grid = $("#example");
    var newRow = "<tr><td>"+act["code"]+"</td><td>"+act["title"]+"</td><td>"+act["type"]+"</td><td>"+act["keywords"]+"</td></tr>";
    $('#example > tbody').append(newRow);
};

$(document).ready(function () {

    $("#searchButton").bind("click", function(){
        $("#grid").show();
    });

    var actJsonformat = JSON.parse('{"act":[{"id":1088102,"code":"1","title":"This is the act 1","keywords":["Agriculture","Spain"],"visibility":"public","type":"directive","event":[{"id":1,"originating_institution":"Commission","destination_institution":["Parliament","Council"],"name":"startProcess","date":"01/01/2016","visibility":"public"}]},{"id":1088103,"code":"2","title":"This is the act 2","keywords":["Fisheries","Ireland"],"visibility":"public","type":"decision","event":[{"id":3,"originating_institution":"Commission","destination_institution":["Parliament","Council"],"name":"adoption","date":"01/02/2016","visibility":"public"}]},{"id":1088104,"code":"3","title":"This is the act 3","keywords":["IT","internal"],"visibility":"private","type":"delegatedAct","event":[{"id":4,"originating_institution":"Parliament","destination_institution":["Parliament"],"name":"plenary","date":"01/03/2016","visibility":"private"}]},{"id":1088105,"code":"4","title":"This is the act 3","keywords":["Home affairs","Justice","Treaty"],"visibility":"public","type":"directive","event":[{"id":7,"originating_institution":"Council","destination_institution":["Commission","Council"],"name":"approve","date":"01/04/2016","visibility":"public"}]}]}');
    
    var acts = actJsonformat["act"];

    for (var index in acts) {
        addRow(acts[index]);
    }
    
});