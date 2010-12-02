/**
 * Functions to execute on loading the document
 */
$(document).ready(function(){
    /**
     * Function to animate the menus
     */
    $('#menu li a').hover(function(){
        width = $(this).width() * (-1);
        $(this).stop().animate({
            'backgroundPosition': '(' + width + ' 0)'
        }, {
            duration: 200
        });
    }, function(){
        $(this).stop().animate({
            'backgroundPosition': '(0 0)'
        }, {
            duration: 200
        });
    });
    
	/**
	 * Loads dynamically content from the pages
	 * when clicking on the menu option
	 */
	 $('#menuContainer li a').click(function(){
		var menuOption =  $(this).attr('href');
		var toLoad = $(this).attr('href') + 'Content';
        $('.menuContent').hide(500);
        $('#'+toLoad+'').load(menuOption+'.html #content', '');
        $('#'+toLoad+'').show(500);

        return false;
    });
});
