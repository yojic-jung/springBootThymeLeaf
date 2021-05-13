/**
 * made by yojic
 */

var sliderIdx = 1;
$(document).on('click',".img-slider-right-btn",function(){
    switch(sliderIdx){
     case 1:
         sliderIdx=2;
         $(".img-slider-content2").css({'margin-left':'0%','left':'100%'});
         $(".img-slider-content2").stop();
         $(".img-slider-content1").clearQueue().animate({
            marginLeft:"-100%"},500, function(){});
         $(".img-slider-content2").clearQueue().animate({
            left:"0%"},500, function(){});
         break;     
     case 2:
         sliderIdx=3;
         $(".img-slider-content3").css({'margin-left':'0%','left':'100%'})
         $(".img-slider-content3").stop();
         $(".img-slider-content2").clearQueue().animate({
            marginLeft:"-100%"},500, function(){});
         $(".img-slider-content3").clearQueue().animate({
            left:"0%"},500, function(){});
          break;     
     case 3:
         sliderIdx=4;
         $(".img-slider-content4").css({'margin-left':'0%','left':'100%'})
         $(".img-slider-content4").stop();
         $(".img-slider-content3").clearQueue().animate({
            marginLeft:"-100%"},500, function(){});
         $(".img-slider-content4").clearQueue().animate({
            left:"0%"},500, function(){});
         break;     
    case 4:
         sliderIdx=1;
         $(".img-slider-content1").css({'margin-left':'0%','left':'100%'})
         $(".img-slider-content1").stop();
         $(".img-slider-content4").clearQueue().animate({
            marginLeft:"-100%"},500, function(){});
         $(".img-slider-content1").clearQueue().animate({
            left:"0%"},500, function(){});
         break;     
    }
});


$(document).on('click',".img-slider-left-btn",function(){

 switch(sliderIdx){
  case 1:
      sliderIdx=2;
      $(".img-slider-content2").css({'margin-left':'0%','left':'-100%'});
      $(".img-slider-content2").stop();
      $(".img-slider-content1").clearQueue().animate({
         marginLeft:"100%"},500, function(){});
      $(".img-slider-content2").clearQueue().animate({
         left:"0%"},500, function(){});
      break;     
  case 2:
      sliderIdx=3;
      $(".img-slider-content3").css({'margin-left':'0%','left':'-100%'})
      $(".img-slider-content3").stop();
      $(".img-slider-content2").clearQueue().animate({
         marginLeft:"100%"},500, function(){});
      $(".img-slider-content3").clearQueue().animate({
         left:"0%"},500, function(){});
       break;     
  case 3:
      sliderIdx=4;
      $(".img-slider-content4").css({'margin-left':'0%','left':'-100%'})
      $(".img-slider-content4").stop();
      $(".img-slider-content3").clearQueue().animate({
         marginLeft:"100%"},500, function(){});
      $(".img-slider-content4").clearQueue().animate({
         left:"0%"},500, function(){});
      break;     
  case 4:
        sliderIdx=1;
        $(".img-slider-content1").css({'margin-left':'0%','left':'-100%'})
        $(".img-slider-content1").stop();
        $(".img-slider-content4").clearQueue().animate({
           marginLeft:"100%"},500, function(){});
        $(".img-slider-content1").clearQueue().animate({
           left:"0%"},500, function(){});
        break;       
    }
});

$(window).resize(function() { $(".img-slider").height($(".img-slider-content1").height());});