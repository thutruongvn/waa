(function ($) {
    $.fn.serializeFormJSON = function () {

        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [{id:o[this.name]}];
                }
                o[this.name].push({id:this.value }|| {});
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
})(jQuery);

$(document).ready(function() {


    $("#btnAddBook").click(function(event){
        event.preventDefault();
        let bookJSON = JSON.stringify($("#bookForm").serializeFormJSON());
        //"{"isbn":"123","title":"Design patterns","author":"AAAAAAAAAAAAA","_categoryList":"1","categoryList":["1","2"]}"

        $.ajax({
            type: "POST",
            url: "http://localhost:8080/book_save_rest",
            data: bookJSON,
            contentType: "application/json",
            dataType: "json",
            success: function(result){
                console.log('success');
                console.log(result);
                $("#errors").empty();
                // make_hidden('errors');
                // add to table tblBooks
                $("#tblBooks").append('<tr><td >' + result.id + '</td>\n' +
                    '        <td >'  + result.isbn + '</td>\n' +
                    '        <td >'  + result.title + '</td>\n' +
                    '        <td >'  + result.author + '</td>\n' +
                    '        <td >'  + result.categoryList + '</td>\n' +
                    '        <td ><a href="/book_details/' + result.id + '">View</a></td></tr>');

            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log('error');
                $("#errors").empty();
                let errorObj = XMLHttpRequest.responseJSON;
                if(errorObj.errorType === 'ValidationError'){
                    let errorArray = errorObj.errors;

                    let errorMessage = "<h3 align=\"center\">Error(s)!!</h3><p>";

                    $.each(errorArray, function(index, e){
                        errorMessage += e.message + "<br />";
                    });
                    errorMessage += "</p>";

                    $('#errors').append(errorMessage);
                    // make_visible('errors');

                }else{
                    alert("error happened not because of validation")
                }


            }
        });

    });

    $("#btnAddReview").click(function(event){
        event.preventDefault();
        let review = $("#reviewForm").serializeFormJSON();
        review.book = {id: review.book};
        let reviewJSON = JSON.stringify(review);
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/review_save_rest",
            data: reviewJSON,
            contentType: "application/json",
            dataType: "json",
            success: function(result){
                console.log('success');
                console.log(result);
                $("#errors").empty();
                // make_hidden('errors');
                // add to table tblBooks
                $("#reviewList").append('<li >\n' +
                    '        <span >'+ result.rate+ '</span>\n' +
                    '        <span >'+ result.reviewer+ '</span>\n' +
                    '        <span >'+ result.comment+ '</span>\n' +
                    '    </li>');

            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log('error');
                $("#errors").empty();
                let errorObj = XMLHttpRequest.responseJSON;
                if(errorObj.errorType === 'ValidationError'){
                    let errorArray = errorObj.errors;

                    let errorMessage = "<h3 align=\"center\">Error(s)!!</h3><p>";

                    $.each(errorArray, function(index, e){
                        errorMessage += e.message + "<br />";
                    });
                    errorMessage += "</p>";

                    $('#errors').append(errorMessage);
                    // make_visible('errors');

                }else{
                    alert("error happened not because of validation")
                }


            }
        });

    });

});


make_visible = function(id) {
    var element = document.getElementById(id);
    element.style.display = 'block';
}

make_hidden = function(id) {
    var element = document.getElementById(id);
    element.style.display = 'none';
}
