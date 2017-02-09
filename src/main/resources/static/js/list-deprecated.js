

function combo(thelist)
{
	  alert("changement de groupe (langue) !")
	  var idx = thelist.selectedIndex;
	  var value = thelist.options[idx].value;
	  window.location = "/?groupname="+value+"&currentpage="+$currenPage;
}

