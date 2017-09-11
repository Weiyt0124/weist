/**
 * Created by 艾泽拉斯国家地理 on 2017/8/2.
 */
$(document).ready( function () {
    $.ajax({
        cache: true,
        type: "post",
        url: "user/findUserName",
//            data : JSON.stringify(param),
        contentType: "application/x-www-form-urlencoded",
        async: false,
        success: function (response) {
            $("#userName").text("亲爱的"+response.userName+",欢迎回来");
        }
    });
});