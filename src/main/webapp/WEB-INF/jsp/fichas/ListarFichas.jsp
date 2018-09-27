<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Monitor de Fichas">
    <meta name="author" content="A&AIngenierosConsultores">
    <link rel="icon" href="<c:url value="imagenes/cns.bmp"/>">

    <title>Fichas</title>

    <link rel="stylesheet" href="<c:url value="css/bootstrap.min.css"/>">
    <style>
        .tarjeta{border-style: solid;border-width: 5px;padding: 3px;}
        .listado {border-style: solid;border-width: 2px;padding: 2px;}
        .borde-azul{border-color: #2cabe3;border-radius: 10px;}
    </style>
  </head>

  <body ng-app="app">

    <div class="container" ng-controller="ayaCtrl">
        <div class="row">
            <div class="col-lg-12">   
                <form>
                    <div class="masthead">
                        <h3 class="text-muted"><i class="glyphicon glyphicon-erase"></i> CNS - Reserva de Citas Previas</h3>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <label>Matricula: </label>
                            <div class="input-group">
                                <input type="text" class="form-control" ng-model="matricula" placeholder="Ej.: 711201RCE...">                          
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <label>Carnet: </label>
                            <div class="input-group">
                                <input type="text" class="form-control" ng-model="carnet" placeholder="Ej.: 1532668...">
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <button class="btn btn-success" ng-disabled="!matricula || !carnet" ng-click="buscar(carnet, matricula)" type="button"><i class="glyphicon glyphicon-search"></i> Buscar</button>
                            <h4 ng-show="pacientes.length">Resultados Totales: {{pacientes.length}}</h4>
                        </div>
                     </div> 
                </form>                                 
            </div>
        </div>  
        <br>
        <div class="jumbotron" ng-show="inicio">
          <div class="row">
            <div class="col-lg-6 col-md-6 text-center">
              <img width="200px" height="210px" class="img-responsive img-thumbnail" src="<c:url value="imagenes/logo.png"/>" alt="CNS">
            </div>
            <div class="col-lg-6 col-md-6">
              <h1><i class="glyphicon glyphicon-erase"></i> Reserva de Citas Previas</h1>
              <p class="lead">Reserva de Citas Previas por Internet, Caja Nacional de Salud</p>            
            </div>
          </div>
        </div>

        <div class="row" ng-if="!inicio">
            <br>
            <cargando></cargando>
            
            <div class="col-lg-12" ng-if="!pacientes.length>0 && !cargando && !fichas.length>0">
                <h2>No existen Resultados</h2>
                <p class="text-danger">Revise si los datos ingresados son correctos.</p>
            </div>     
            
            <div ng-if="pacientes.length>0">                
                <div class="col-lg-4 col-md-4" ng-repeat="paciente in pacientes">
                    <div class="tarjeta borde-azul text-center">
                        <h3><i class="glyphicon glyphicon-user"></i> <br> <span class="text-info">{{paciente.nombres}}</span></h3>
                        <h4><span class="text-success"><strong>Carnet:</strong> {{paciente.carnet}}</span></h4>                 
                        <div class="well">
                          <p><strong>HCL: </strong>{{paciente.hcl}}</p>
                          <p><strong>FEC. NAC: </strong>{{paciente.fec_nacimiento | date:'yyyy-mm-dd'}}</p>
                          <p><strong>MATRICULA: </strong>{{paciente.nro_registro}}</p>
                          <p><strong>SEXO: </strong>{{buscarSexo(paciente.id_tipo_sexo)}}</p>                    
                          <form method="POST" action='<c:url value="/fichas"/>'>                         
                              <button class="btn btn-block btn-primary"><i class="glyphicon glyphicon-erase"></i> Ficha</button>
                              <input type="text" style="display: none;" name="id_paciente" ng-model="paciente.id_paciente">
                              <input type="hidden" name="accion" value='Ficha'>               
                          </form>
                        </div>                  
                    </div>            
                </div>
            </div>
                              
            <div class="col-lg-12" ng-if="fichas.length>0">
                <ul class="list-group success">
                    <li ng-class="{'list-group-item': ficha.nro_registro!==matricula,'list-group-item listado borde-azul': ficha.nro_registro===matricula}" ng-repeat="ficha in fichas">
                        <span class="badge success pull-left">{{$index+1}} </span> <br>
                        <p>
                            <label> Fecha - Hora:</label> {{ficha.fecha | date:'dd/MM/yyyy'}} - <strong class="text-primary">{{ficha.fecha | date:'HH:mm'}}</strong> | 
                            <label>Matricula:</label> {{ficha.nro_registro}}
                        </p>
                        <p>
                            <label>Estado:</label>
                            <span ng-show="ficha.id_riesgo===1" class="text-danger"> EN ESPERA</span>
                            <span ng-show="ficha.id_riesgo===0" class="text-success"> YA ATENDIDO</span>
                        </p>
                    </li>
                </ul>
            </div>                  
            
        </div>
                        
        <hr>
        <footer class="footer">
          <p>CNS &copy; 2018</p>
        </footer>
    </div>

    <script src="<c:url value="js/jquery.js"/>"></script>
    <script src="<c:url value="js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="js/angular.min.js"/>"></script>
    <script>
    'use strict';
    var app = angular.module('app',[]);
    
    app.directive('cargando', function () {
        return {
            restrict: 'E',
            replace:true,
            template: '<div ng-show="cargando" class="alert"><div class="text-center"><h3><i class="glyphicon glyphicon-hourglass"></i> Cargando...</h3> </div> </div> ',
            link: function (scope, element, attr) {
                scope.$watch('cargando', function (val) {
                    if (val)$(element).show();else $(element).hide();
                });
            }
        };
    });
    
    app.controller('ayaCtrl', function($scope, $http) {
        console.log('ayaCtrl...');
        
        $scope.inicio = true;
        $scope.cargando = false;
        $scope.buscar = (carnet, matricula)=>{
            $scope.cargando = true;
            $scope.inicio = false;
            $http.get('http://localhost:8084/hospital/api/fichasinternet/fichas?matricula='+matricula+'&carnet='+carnet)
            .then((response) => {
                if(response.data.length>0){
                    $scope.fichas = response.data;
                    $scope.cargando = false;
                }else{
                    $scope.cargando = true;
                    $scope.fichas = [];
                    $http.get('<c:url value="/api/fichasinternet/pacientes?matricula="/>'+matricula+'&carnet='+carnet)
                    .then((response) => {
                        $scope.pacientes = response.data;      
                        $scope.cargando = false;
                    });
                }   
            });
        };
        
        $scope.buscarSexo = (id) =>{
            let sexo = 'MASCULINO';
            if(id===1){
                sexo = 'FEMENINO';
            };
            return sexo;
        };     

    });

    </script>
  </body>
</html>
