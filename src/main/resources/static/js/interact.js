var actJsonformatPrivate = JSON.parse('{"act":[{"id":1088102,"code":"1","title":"This is the act 1","keywords":["Agriculture","Spain"],"visibility":"public","type":"directive","event":[{"id":1,"originating_institution":"Commission","destination_institution":["Parliament","Council"],"name":"startProcess","date":"01/01/2016","visibility":"public"}]},{"id":1088103,"code":"2","title":"This is the act 2","keywords":["Fisheries","Ireland"],"visibility":"public","type":"decision","event":[{"id":3,"originating_institution":"Commission","destination_institution":["Parliament","Council"],"name":"adoption","date":"01/02/2016","visibility":"public"}]},{"id":1088104,"code":"3","title":"This is the act 3","keywords":["IT","internal"],"visibility":"private","type":"delegatedAct","event":[{"id":4,"originating_institution":"Parliament","destination_institution":["Parliament"],"name":"plenary","date":"01/03/2016","visibility":"private"}]},{"id":1088105,"code":"4","title":"This is the act 3","keywords":["Home affairs","Justice","Treaty"],"visibility":"public","type":"directive","event":[{"id":7,"originating_institution":"Council","destination_institution":["Commission","Council"],"name":"approve","date":"01/04/2016","visibility":"public"}]}]}');
var actJsonformatPublic = JSON.parse('{"act":[{"id":1088102,"code":"1","title":"This is the act 1","keywords":["Agriculture","Spain"],"visibility":"public","type":"directive","event":[{"id":1,"originating_institution":"Commission","destination_institution":["Parliament","Council"],"name":"startProcess","date":"01/01/2016","visibility":"public"}]},{"id":1088103,"code":"2","title":"This is the act 2","keywords":["Fisheries","Ireland"],"visibility":"public","type":"decision","event":[{"id":3,"originating_institution":"Commission","destination_institution":["Parliament","Council"],"name":"adoption","date":"01/02/2016","visibility":"public"}]},{"id":1088105,"code":"4","title":"This is the act 3","keywords":["Home affairs","Justice","Treaty"],"visibility":"public","type":"directive","event":[{"id":7,"originating_institution":"Council","destination_institution":["Commission","Council"],"name":"approve","date":"01/04/2016","visibility":"public"}]}]}');
var actDetailJsonformatPrivate = JSON.parse('{"act":[{"id":1088102,"code":"1","title":"This is the act 1","keywords":["Agriculture","Spain"],"visibility":"public","type":"directive","event":[{"id":1,"originating_institution":"Commission","destination_institution":["Parliament","Council"],"name":"startProcess","date":"01/01/2016","visibility":"public"},{"id":2,"originating_institution":"Commission","destination_institution":["Parliament","Council"],"name":"expertGroup","date":"01/03/2016","visibility":"private"}]}]}');
var actDetailJsonformatPublic = JSON.parse('{"act":[{"id":1088102,"code":"1","title":"This is the act 1","keywords":["Agriculture","Spain"],"visibility":"public","type":"directive","event":[{"id":1,"originating_institution":"Commission","destination_institution":["Parliament","Council"],"name":"startProcess","date":"01/01/2016","visibility":"public"}]}]}');

var USERROLE = 'NONE';

var liveData = true;

var buildAppUrl = function(relativePath) {

    var url = window.location.href; // http://104.40.156.209:8080 - localhost..

    return url + relativePath;
}

function addRow(act){
    var grid = $("#example");
    var newRow = "<tr data-value='" + act["id"] + "'><td><span class='delegatedActCode'>" + act["code"] + "</span></td><td>" + act["title"] + "</td><td>" + act["type"] + "</td><td>" + act["keywords"] + "</td></tr>";
    $('#example > tbody').append(newRow);
};

function drawTimeline(actId){

    var actTimeline = actDetailJsonformatPublic["act"][0];
    if (USERROLE==='INSTITUTION') {
        actTimeline = actDetailJsonformatPrivate["act"][0];
    }

    var events = actTimeline.event;
    var actName =actTimeline.title;
    var htmlString = '';
    for (var index in events){
      htmlString = '<span class="timeline-label"><span class="glyphicon glyphicon-triangle-bottom" aria-hidden="true"> </span></span>';
      $('#timelineContainer').append(htmlString);
        htmlString = '<div class="timeline-item timeline-item-arrow-sm"><span class="timeline-label"></span><div class="timeline-event timeline-event-primary"><div class="panel panel-default"><div class="panel-heading"><div class="panel-title"> <h6>';
        htmlString += events[index].date+'</h6><h4>ACT: '+actName+' | Name: '+events[index].name+'</h4></div>';
        htmlString += '</div></div><div class="panel-body"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span> ';
        htmlString += events[index].originating_institution;
        htmlString += ' <span class="glyphicon glyphicon-play" aria-hidden="true"></span> ';
        htmlString += events[index].destination_institution.join();
        htmlString += '</div></div></div>';
        $('#timelineContainer').append(htmlString);

    }

    $('#timelineContainer').append('<span class="timeline-label"><span class="glyphicon glyphicon-ban-circle" aria-hidden="true"> </span></span>');
}

/**
 * Called when the user presses enter on the search box, or on the search loupe button
 */
function submitSearch() {
    $("#grid").show();
    $("html").animate({
        scrollTop: $("#grid").position().top
    }, 600);
}

$(document).ready(function () {

    $.ajax({
            type: "GET",
            //url: "http://104.40.156.209:8080/user",
            url: buildAppUrl("/user"),
            async : false,
            success: function(data) {
                if (data === 'INSTITUTION') {
                    $('#loginLinkDetails').empty();
                    $('#loginLinkDetails').append('<a id="linkDetailLogout"><span class="glyphicon glyphicon-log-out"></span> Logout</a>');
                    $('#linkDetailLogout').bind("click", function(event) {
                        $.ajax({
                            url: "/logout",
                            success: function() {
                                window.location.href = "/";
                            }
                        });
                    });
                    USERROLE = 'INSTITUTION';
                } else if (data === 'THIRDPARTY') {
                    $('#loginLinkDetails').empty();
                    $('#loginLinkDetails').append('<a id="linkDetailLogout"><span class="glyphicon glyphicon-log-out"></span> Logout</a>');
                    $('#linkDetailLogout').bind("click", function(event) {
                        $.ajax({
                            //url: "http://104.40.156.209:8080/logout",
                            url: buildAppUrl("/logout"),
                            success: function() {
                                window.location.href = "/";
                            }
                        });
                    });
                    USERROLE = 'THIRDPARTY';
                } else {
                    USERROLE = 'NONE';
                }
            }
        });

    //show grid and populate
    $('#searchField').keypress(function(e) {
        if (e.which == '13') {
            submitSearch();
        }
    });

    $("#searchButton").bind("click", function(){
        submitSearch();
    });

    var url = buildAppUrl("/v2/acts/public/list");
    if (USERROLE==='INSTITUTION') {
        url =  buildAppUrl("/v2/acts/private/list");
    }

    if(liveData){
      //get data from server http://10.40.11.237:8080/v2/acts/private/list    http://10.40.11.240:8080/v2/acts/private/list
      $.getJSON( url, function( data ) {

        $("#table").append('<div class="table-responsive"><table id="example" class="table table-striped table-responsive table-hover display responsive nowrap" width="100%" cellspacing="0"><thead><tr><th data-priority="1">Code</th><th data-priority="2">Title</th><th data-priority="3">Type</th><th data-priority="4">Keywords</th></tr></thead><tbody></tbody></table></div>');

          for (var index in data) {
              addRow(data[index]);
          }

          var table = $('#example').DataTable({
              //dom: 'lBfrtip',
              //dom: 'Blf<t><"d-info"i>p',
              responsive: true,
              buttons: [
                  'copyHtml5',
                  'excelHtml5',
                  'csvHtml5',
                  'pdfHtml5'
              ]
          });
          $('.d-panel-print').append(table.buttons().container());
          $('.d-panel-print').append('<div style="clear: both"></div>');

            var isClick = 0;
            //show timeline and populate
            $(".delegatedActCode").click(function() {
                if (isClick == 1) {
                    var actId = $(this).attr("data-value");
                    $('#timelineModal').modal('show');
                    drawTimeline(actId);
                }
            }).mousedown(function () {
                isClick = 1;
            }).mousemove(function () {
                isClick = 0;
            });

       });

       $('#btn-login').bind("click", function() {
           $.ajax({
               type: "POST",
               url: "/login",
               data: {
                   username: $('#login-username').val(),
                   password: $('#login-password').val()
               },
               success: function() {
                   window.location.href = "/";
               }
           });
       })

     } else {
      var acts = actJsonformat["act"];

       for (var index in acts) {
           addRow(acts[index]);
       }

       $('#btn-login').bind("click", function() {
           $.ajax({
               type: "POST",
               url: "/login",
               data: {
                   username: $('#login-username').val(),
                   password: $('#login-password').val()
               },
               success: function() {
                   window.location.href = "/";
               }
           });
       })
     }
});
