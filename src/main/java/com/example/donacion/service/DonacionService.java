/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.donacion.service;


import com.example.donacion.modelo.Donacion;
import com.example.donacion.repository.DonacionRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author FLHORIAN
 */
@Service
public class DonacionService {
     
    @Autowired
    private DonacionRepository repository;
    
    public List<Donacion> ListarTodas(){
         return repository.findAll();
    }
    
     public void guardar(Donacion donacion){
        repository.save(donacion);
        
        
    }
     
      /***
     *FUNCION PARA  BUSCAR REGISTRO EMPLEADO POR ID
     *@param id
     *@return
     */
     public Optional<Donacion> buscarPorId(Long id){
        return repository.findById(id);
    }
       public void eliminar(Long id) {
        repository.deleteById(id);
    }

     
}