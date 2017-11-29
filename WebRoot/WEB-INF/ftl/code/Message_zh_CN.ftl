id.required=${entityTitle}ID不能为空
addSuccess=新增${entityTitle}信息成功~
addFailed=新增${entityTitle}信息成功~
editSuccess=编辑${entityTitle}信息成功~
editFailed=编辑${entityTitle}信息失败~
delSuccess=删除${entityTitle}信息成功~
delFailed=删除${entityTitle}信息失败~
restoreSuccess=从回收站还原${entityTitle}信息成功~
restoreFailed=从回收站还原${entityTitle}信息失败~
auditSuccess=审核${entityTitle}信息成功~
auditFailed=审核${entityTitle}信息失败~
commendSuccess=推荐${entityTitle}信息成功~
commendFailed=推荐${entityTitle}信息失败~
hotSuccess=设置${entityTitle}信息热门成功~
hotFailed=设置${entityTitle}信息热门失败~
batchSuccess=批量操作${entityTitle}信息成功~
batchFailed=批量操作${entityTitle}信息失败~
<#if attrList ??>
<#list attrList as attr>
${attr.attrName}.required=${attr.attrComment}不能为空
</#list>
</#if>