/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.donacion.repository;


import com.example.donacion.modelo.Donacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author FLHORIAN
 */
@Repository
public interface DonacionRepository extends JpaRepository<Donacion, Long>{
    
}
