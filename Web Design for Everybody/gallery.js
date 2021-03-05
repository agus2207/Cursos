	function upDate(previewPic){
	  var url = previewPic.src;
	  document.getElementById("image").style.backgroundImage = "url('"+url+"')";
	  var alt = previewPic.alt;
	  document.getElementById("image").innerHTML = alt;
  
	}

	function unDo(){
		document.getElementById("image").style.backgroundImage = "url('')";
		document.getElementById("image").innerHTML = "Hover over an image below to display here.";
		
	}