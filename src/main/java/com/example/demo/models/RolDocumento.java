//concepto global para los modelos, todos lo pueden usar, se usa en DocumentoUsuario y DocumentoUsuarioId
//se lo desacoplo de DocumenetoUsuario por si en algun momento se necesita realizar consulta segun el rol del usuario
//en el documento el codigo sea mas limpio con menos . de acceso
package com.example.demo.models;

    public enum RolDocumento {
        AUTOR,      // El que crea el contenido original
        EDITOR,     // El que tiene permiso para modificarlo
        SUPERVISOR  // El que revisa y da el visto bueno fina
    }

