/*Add the JavaScript here for the function billingFunction().  It is responsible for setting and clearing the fields in Billing Information */
function billingFunction(){
  var state = document.getElementById("same").checked;
  if(state == true){
    var sname = document.getElementById("shippingName").value;
    var szip = document.getElementById("shippingZip").value;
    document.getElementById("billingName").value = sname;
    document.getElementById("billingZip").value = szip;
  }
  else{
    document.getElementById("billingName").value = "";
    document.getElementById("billingZip").value = "";
  }
}