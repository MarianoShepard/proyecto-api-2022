--a) Investiga ¿Qué es un API? y ¿Qué es un microservicio? 
Una API es un conjunto de protocolos y definiciones que facilitan la implementación de los servicios de esta API con una nueva arquitectura de software, sin que esta arquitectura tenga que saber necesariamente cómo están implementados estos servicios. Es decir, esto mejora y hace más fácil la instalación de servicios dentro de un programa.
Los microservicios son partes del software que ofrecen modularidad al sistema, es decir, que el programa no es de arquitectura monolítica, en el que si falla un servicio, es muy seguro que todo el sistema se caiga. Los microservicios funcionan independientes entre sí, y realizan sus tareas necesarias sin interferir directamente en el trabajo de otros microservicios. 

--b) Investiga que es Swagger. ¿Para qué se utiliza? Swagger es un framework para documentación de APIs desarrolladas en el paradigma REST. Este framework se integra al programa y te permite describir, producir, consumir y visualizar APIs.


--c) Descarga el proyecto del API que se encuentra en Github. En el foro se indicará la dirección de Github. 










--d) Completa los endpoints que hacen falta. 
--e) Ejecuta el proyecto.

 a) Ejecutar la aplicación y navegar a http://localhost:8090 en el navegador de tu elección.


b) Navegar al explorador de Swagger integrado, con http://localhost:8090/docs


c) Ejecuta el endpoint GET /products/all. ¿Qué devuelve y en qué formato?

Devuelve lo equivalente a una sentencia “SELECT * FROM productos” pero en formato .json

d) Prueba hacer una inserción a la tabla de productos, ejecutando el endpoint POST /product/add



e) Crea el endpoint /clients/all que muestre todos los clientes. Deberás tomar como referencia el endpoint GET /products/all. Para ello deberás:

e.1) Crear el archivo ClientDto.java en la carpeta dto. 
e.2) Modificar el archivo StoreDao.java para incluir el nuevo query getClients(). Deberás mapear ClienteDto de manera similar a:

@RegisterBeanMapper(ProductDto.class)
@SqlQuery("SELECT * FROM productos")
List<ProductDto> getProducts();



e.2.1) ¿Para qué sirve la directiva @RegisterBeanMapper (https://jdbi.org/#_beanmapper)?
Ayuda a reducir el acoplamiento entre la presentación (es decir, el esquema XML) y la lógica comercial.

e.2.2) ¿Qué deberá devolver el método getClients?
Deberá devolver, como el método getProducts(), el mismo formato pero mostrando todos los clientes de la tabla Clientes.

e.3) Deberás modificar el archivo StoreDal.java y crear el método getClients() de manera similar a:

public ResponseDto<List<ProductDto>> getProducts() {

        ResponseDto responseDto = new ResponseDto<List<ProductDto>>();
        responseDto.setSuccess(true);
        Jdbi jdbi = jdbiService.getInstance();
        var products = jdbi.withExtension(StoreDao.class, dao -> dao.getProducts());
        responseDto.setData(products);
        return responseDto;
    }



e.4) Deberás crear el archivo ClientsResource.java en la carpeta src/main/java/resources junto a ProductsResources.java El archivo ClientsResource.java deberá contener un método getClients() similar a:

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all products")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
    })
    public Response getProducts() {

        var responseDto = storeDal.getProducts();
        return Response.ok(responseDto).build();
    }



Nota, el path  @Path("/products") deberá ser modificado a clients en el archivo ClientsResource.java

e.5) Compila el proyecto con el comando  ./mvnw.cmd clean compile


e.6) Y ejecútalo ./mvnw.cmd quarkus:dev
e.7) Navega a http://localhost:8090/docs y verifica que se muestra el nuevo endpoint.

