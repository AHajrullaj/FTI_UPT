$(document).ready(function(){
  $("#hint").autocomplete({
      source: "/autocomplete",
      minLength: 1
  });
});