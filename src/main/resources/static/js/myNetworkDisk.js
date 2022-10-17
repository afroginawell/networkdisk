$(function () {
    $("li:last").addClass("active");
    var shareid = getUrlParam("shareid");
    if(shareid){
        alert("文件分享ID是 "+shareid+" 可以在我的分享中查看！");
    }
// 点击“上传文件“按钮时，触发表单中的浏览文件的操作
    $("#upload").click(function () {
        $("#uploadFile").click();
    })
// 选择好上传的文件后，执行提交表单的操作
    $("#uploadFile").change(function () {
        $("#uploadForm").submit();
    })
})

function rename(obj){
    var newName = prompt("请输入新的名字：");
    if(newName){
        // alert("renameFile?fileid="+$(obj).attr("id")+"&newName="+newName);
        location.href="/networkdisk/user/renameFile?fileid="+$(obj).attr("id")+"&newName="+newName;
    }
}

//获取url中的参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg); //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}
