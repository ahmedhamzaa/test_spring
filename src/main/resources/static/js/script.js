$(document).ready(function(){
    $('#acc').click(
        function() {

            $('#acc').css('background-color', 'white');
            $('#acc h1').css('color', '#6D5DD3');
            $('#pan h1').css('color', '#FDFDFD');
            $('#hist h1').css('color', '#FDFDFD');
            $('#hist').css('background-color', ' #6D5DD3');
            $('#pan').css('background-color', ' #6D5DD3');
            $('#beforeacc').css('border-bottom-right-radius', '25px');
            $('#hist').css('border-top-right-radius', '25px');
            $('#hist').css('border-bottom-right-radius', '0px');
            $('#acc').css('border-bottom-right-radius', '0px');
            $('#pan').css('border-top-right-radius', '0px');
            $('#afterpan').css('border-top-right-radius', '0px');
        }
    )
    $('#hist').click(
        function() {

            $('#hist').css('background-color', 'white');
            $('#hist h1').css('color', '#6D5DD3');
            $('#acc h1').css('color', '#FDFDFD');
            $('#pan h1').css('color', '#FDFDFD');
            $('#pan').css('background-color', ' #6D5DD3');
            $('#acc').css('background-color', '#6D5DD3');
            $('#acc').css('border-bottom-right-radius', '25px');
            $('#pan').css('border-top-right-radius', '25px');
            $('#beforeacc').css('border-bottom-right-radius', '0px');
            $('#hist').css('border-top-right-radius', '0px');
            $('#hist').css('border-bottom-right-radius', '0px');
            $('#afterpan').css('border-top-right-radius', '0px');
        }
    )
    $('#pan').click(
        function() {

            $('#pan').css('background-color', 'white');
            $('#pan h1').css('color', '#6D5DD3');
            $('#hist h1').css('color', '#FDFDFD');
            $('#acc h1').css('color', '#FDFDFD');
            $('#hist').css('background-color', '#6D5DD3');
            $('#acc').css('background-color', '#6D5DD3');
            $('#afterpan').css('border-top-right-radius', '25px');
            $('#hist').css('border-bottom-right-radius', '25px');
            $('#hist').css('border-top-right-radius', '0px');
            $('#acc').css('border-bottom-right-radius', '0px');
            $('#pan').css('border-top-right-radius', '0px');
            $('#beforeacc').css('border-bottom-right-radius', '0px');
        }
    )
})