
jQuery(document).ready(function() {

    /*
        Background slideshow
    */
    $.backstretch([
      "assets/img/backgrounds/1.jpg"
    , "assets/img/backgrounds/2.jpg"
    , "assets/img/backgrounds/3.jpg"
    ], {duration: 3000, fade: 750});

    /*
        Tooltips
    */
    $('.links a.home').tooltip();
    $('.links a.blog').tooltip();

    /*
        Form validation
    */
    $('.register form').submit(function(){
        $(this).find("label[for='firstname']").html('Nombre');
        $(this).find("label[for='lastname']").html('Apellido');
        $(this).find("label[for='username']").html('Usuario');
        $(this).find("label[for='email']").html('Email');
        $(this).find("label[for='password']").html('Contraseña');
        $(this).find("label[for='user']").html('Usuario');
        $(this).find("label[for='pass']").html('Contraseña');
        ////
        var firstname = $(this).find('input#firstname').val();
        var lastname = $(this).find('input#lastname').val();
        var username = $(this).find('input#username').val();
        var email = $(this).find('input#email').val();
        var password = $(this).find('input#password').val();
        var user = $(this).find('input#user').val();
        var pass = $(this).find('input#pass').val();

        if(firstname == '') {
            $(this).find("label[for='firstname']").append("<span style='display:none' class='red'> - Por favor ingrese su nombre.</span>");
            $(this).find("label[for='firstname'] span").fadeIn('medium');
            return false;
        }
        if(lastname == '') {
            $(this).find("label[for='lastname']").append("<span style='display:none' class='red'> - Por favor ingrese su apellido.</span>");
            $(this).find("label[for='lastname'] span").fadeIn('medium');
            return false;
        }
        if(username == '') {
            $(this).find("label[for='username']").append("<span style='display:none' class='red'> - Por favor ingrese su usuario.</span>");
            $(this).find("label[for='username'] span").fadeIn('medium');
            return false;
        }
        if(email == '') {
            $(this).find("label[for='email']").append("<span style='display:none' class='red'> - Por favor ingrese su email.</span>");
            $(this).find("label[for='email'] span").fadeIn('medium');
            return false;
        }
        if(password == '') {
            $(this).find("label[for='password']").append("<span style='display:none' class='red'> - Por favor ingrese su contraseña.</span>");
            $(this).find("label[for='password'] span").fadeIn('medium');
            return false;
        }

         if(user == '') {
            $(this).find("label[for='user']").append("<span style='display:none' class='red'> - Por favor ingrese su usuario.</span>");
            $(this).find("label[for='user'] span").fadeIn('medium');
            return false;
        }
        if(pass == '') {
            $(this).find("label[for='pass']").append("<span style='display:none' class='red'> - Por favor ingrese su contraseña.</span>");
            $(this).find("label[for='pass'] span").fadeIn('medium');
            return false;
        }

    });

   /*
        LOGIN validation
    */
    $('.login form').submit(function(){
      
        $(this).find("label[for='user']").html('Usuario');
        $(this).find("label[for='pass']").html('Contraseña');
        ////
       
        var user = $(this).find('input#user').val();
        var pass = $(this).find('input#pass').val();

        if(user == '') {
            $(this).find("label[for='user']").append("<span style='display:none' class='red'> - Por favor ingrese su usuario.</span>");
            $(this).find("label[for='user'] span").fadeIn('medium');
            return false;
        }
        if(pass == '') {
            $(this).find("label[for='pass']").append("<span style='display:none' class='red'> - Por favor ingrese su contraseña.</span>");
            $(this).find("label[for='pass'] span").fadeIn('medium');
            return false;
        }

    });

});


