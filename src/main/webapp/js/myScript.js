$(document).ready(function () {
	
    const loadListarProducto = () => {
		$("#listarProductos").load('/productos_limpieza/inicio');
	}
    
    
    $("#listarProductos").click(function (event) {
        console.log("Click listar producto");
        loadListarProducto();
    });

});