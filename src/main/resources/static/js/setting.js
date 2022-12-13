$(function (){
    $("#uploadForm").submit(upload);
})

function upload(){
    $.ajax({
        url: "http://upload-as0.qiniup.com",
        method: "post",
        processData: false, // 不将表单内容转换为字符串
        contentType: false, // 浏览器自动设置上传类型
        data: new FormData($("#uploadForm")[0]),
        success: function (data){
            if (data && data.code == 0){
                // 更新头像访问路径
                $.post(
                    CONTEXT_PATH + "/user/header/url",
                    {"fileName": $("input[name='key']").val()},
                    function(data){
                        data = $.parseJSON(data);
                        if (data.code == 0){
                            window.location.reload();
                        }else{
                            alert(data.msg);
                        }
                    }
                );
            }else {
                alert("头像上传失败!");
            }
        }
    });
    // 事件到此为止, 不要再执行底层的命令了
    return false;
}