$(document).ready(function() {
  $.ajax({
    url : "/design/recent"
  }).then(function(data) {
    if (data.length > 0) {
      $.get('mustache/recents.mst', function(template) {
        var rendered = Mustache.render(template, data);
        $('#recents').html(rendered);
      });
    } else {
      $('#recents').html("<p>No tacos have been designed yet..." +
          "be the first to create a tasty masterpiece!</p>");
    }
  });
});