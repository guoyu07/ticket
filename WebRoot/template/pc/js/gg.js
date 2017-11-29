var rollText_k=6; //菜单总数
var rollText_i=1; //菜单默认值
//setFocus1(0);
rollText_tt=setInterval("rollText(1)",8000);
function rollText(a){
clearInterval(rollText_tt);
rollText_tt=setInterval("rollText(1)",8000);
rollText_i+=a;
if (rollText_i>rollText_k){rollText_i=1;}
if (rollText_i==0){rollText_i=rollText_k;}
//alert(i)
 for (var j=1; j<=rollText_k; j++){
 document.getElementById("rollTextMenu"+j).style.display="none";
 }
 document.getElementById("rollTextMenu"+rollText_i).style.display="block";
 document.getElementById("pageShow").innerHTML = rollText_i+"/"+rollText_k;
}