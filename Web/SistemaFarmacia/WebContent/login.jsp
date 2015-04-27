<!Doctype html>
<html>
<head>
<meta charset="utf-8">
<meta content='IE=edge,chrome=1' http-equiv='X-UA-Compatible' />
<title>Shop Medical S.A.C.</title>
	<link rel="shortcut icon" href="images/icono.ico" />
	<link rel="stylesheet" href="css/jquery.maximage.css" type="text/css" media="screen" charset="utf-8" />
	<link rel="stylesheet" href="css/screen.css" type="text/css" media="screen" charset="utf-8" />
    <link rel="stylesheet" href="css/styles.css" type="text/css" charset="utf-8" />
	<link rel="stylesheet" href="Bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="Bootstrap/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="Bootstrap/css/jquery-ui.min.css">
	<!--[if lt IE 9]><script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
	
	<!--[if IE 6]>
	<style type="text/css" media="screen">
			/*I don't feel like messing with pngs for this browser... sorry*/
			#gradient {display:none;}
	</style>
	<![endif]-->
	<link rel="stylesheet" href="Bootstrap/css/bootstrap.min.css">
    <script src='http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.js'></script>
	<script src="js/jquery.cycle.all.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/jquery.maximage.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/extra.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
		<div id="maximage">
			<img src="images/01.png" width="1400" height="1050" />
			<img src="images/02.png" width="1400" height="1050" />
			<img src="images/03.png" width="1400" height="1050" />
            <img src="images/04.png" width="1400" height="1050" />
            <img src="images/05.png" width="1400" height="1050" />
		</div>
        <div id="cuerpo">
       	      		 <div id="headerLogin"> </div>
                    		<div id="logofondo">
             					<img src="images/logoLM2.png" id="logo">
            				</div>
              			      
              		
            <div class="register-container container">
            <div class="row">
                <div id="form">
                    <form action="<%=request.getContextPath()%>/Login" method="post">
                        <div id="iniciologin"><h2>INICIAR SESION</h2></div>
                        <br><br>
                        <label style="width:100px;">Usuario</label>
                        <input type="text" class="textbox" name="user" placeholder="Ingrese su usuario" required="required">
                        <br><br>
                        <label style="width:100px;">Contraseña</label>
                        <input type="password" class="textbox" name="pass" placeholder="Ingrese su contraseña" required="required">
                        <br><br>
                       <button  class="btn-large clickSubmit btn btn-primary" type="submit">Iniciar Sesi&oacute;n</button>
                    </form>
                </div>
            </div>
            </div>
        </div>
		<footer id="contenedor-pie">
            <p id="piepagina">Shop Medical S.A.C 2015 Todos los derechos reservados</p>
        </footer>
</body>
</html>
