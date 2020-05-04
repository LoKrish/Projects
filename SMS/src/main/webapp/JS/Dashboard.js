$(document).ready(function() {
  $('.mobilenav').click(function() {
    $('.nav').addClass('showing');
    $('.navli a').addClass('ismobile');
  })
  
  $('.navli a').click(function() {
    if ($(this).hasClass('ismobile')) {
      $('.nav').removeClass('showing');
    }
    $('.navli').removeClass('active');
    $(this).parent().addClass('active');
  })
})