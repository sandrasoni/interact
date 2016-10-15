function addRow(element){
    console.log(element);
};

$(document).ready(function () {

    $("#searchButton").bind("click", function(){
        $("#grid").show();
    });

    var actJsonformat = JSON.parse('{"act": [{"id": "1088102","code": "1","title": "This is the act 1","keywords": ["Agriculture", "Spain"],"visibility": "public","type": "directive","event": [{"id": 1,"originating_institution": "Commission","destination_institution": ["Parliament", "Council"],"name": "startProcess","date": "01/01/2016","visibility": "public"}]}]}');
    
    actJsonformat.foreach(addRow());


    var grid = $("#example");

});