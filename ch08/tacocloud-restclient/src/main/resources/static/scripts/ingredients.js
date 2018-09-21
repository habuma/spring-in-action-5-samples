$(document).ready(function() {
  $.ajax({
    url : "/ingredients?byType"
  }).then(function(data) {
    $.get('mustache/ingredients.mst', function(template) {
      $('#wraps').html(Mustache.render(template, data.wrap))
      $('#proteins').html(Mustache.render(template, data.protein))
      $('#cheeses').html(Mustache.render(template, data.cheese));
      $('#veggies').html(Mustache.render(template, data.veggies));
      $('#sauces').html(Mustache.render(template, data.sauce));
    });
  });
});