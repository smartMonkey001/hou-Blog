$(function () {
    $("#indexBackground").attr("style","margin-top:-200px;background: url('/img/b"+Math.round(Math.random()*(8-1)+1)+".png')  center center / cover no-repeat;")
    RandomlyGetFamousQuotes();
    BlogsShowList();
    TagShowList();
    queryCount();
})
$(document).on("click","#backToTheTop",function () {
    $(window).scrollTo(0,500);
})

var waypoint = new Waypoint({
    element: document.getElementById('archiveBody'),
    handler: function(direction) {
        if (direction=='down'){
            $("#backToTheTop").removeClass("animate__animated animate__bounceOutRight");
            $("#backToTheTop").addClass("animate__animated animate__bounceInRight");
            $("#backToTheTop").show();
        }else {
            $("#backToTheTop").hide();
            $("#backToTheTop").removeClass("animate__animated animate__bounceInRight");
            $("#backToTheTop").addClass("animate__animated animate__bounceOutRight");
            $("#backToTheTop").show();
        }
    }
})
$(document).on("keyup","#Search_for_articles",function () {
    if(event.keyCode==13){
        window.location.href="/toblogSearch?title="+$.trim($("#Search_for_articles").val());
    }
})

$(document).on("click","#search_blog_btn",function () {
    window.location.href="/toblogSearch?title="+$.trim($("#Search_for_articles").val());
})