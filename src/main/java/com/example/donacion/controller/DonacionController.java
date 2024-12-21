/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.donacion.controller;

import com.example.donacion.modelo.Donacion;
import org.springframework.ui.Model;
import com.example.donacion.service.DonacionService;
import com.itextpdf.kernel.colors.ColorConstants;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// pdf
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

// importaciones excel
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author FLHORIAN
 */
@Controller
@RequestMapping("/donaciones")
public class DonacionController {

    private final DonacionService service;

    public DonacionController(DonacionService donacionService) {
        this.service = donacionService;
    }

    @GetMapping
    public String ListarDonaciones(Model model) {
        model.addAttribute("donaciones", this.service.ListarTodas());
        return "donaciones";
    }

    @GetMapping("/nueva")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("donacion", new Donacion());
        return "formulario";
    }

    @PostMapping
    public String guardarProductos(@ModelAttribute Donacion donacion) {
        this.service.guardar(donacion);
        return "redirect:/donaciones";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute("donacion", this.service.buscarPorId(id).orElseThrow(() -> new IllegalArgumentException("ID invalido" + id)));
        return "formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProductos(@PathVariable Long id) {
        this.service.eliminar(id);
        return "redirect:/donaciones";
    }

    @DeleteMapping("/{id}")
    public String eliminarPersonas(@PathVariable Long id) {
        this.service.eliminar(id);
        return "redirect:/donaciones";
    }

    @GetMapping("/reporte/pdf")
    public void generarPdf(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=donaciones_reporte.pdf");

        PdfWriter write = new PdfWriter(response.getOutputStream());
        Document document = new Document(new com.itextpdf.kernel.pdf.PdfDocument(write));

// Título del reporte
        document.add(new Paragraph("Reporte de Donaciones")
                .setBold()
                .setFontSize(18)
                .setTextAlignment(TextAlignment.CENTER));

        // Ajustes de márgenes y tamaño de fuente 
        document.setMargins(20, 20, 20, 20);
        document.setFontSize(9);

        // Tabla con 10 columnas
        Table table = new Table(8);
        table.setWidth(UnitValue.createPercentValue(100))
                .setMarginTop(20)
                .setBorder(new SolidBorder(ColorConstants.BLACK, 1));

        // Encabezados de la tabla
        table.addHeaderCell(new Paragraph("ID").setBold());
        table.addHeaderCell(new Paragraph("Producto").setBold());
        table.addHeaderCell(new Paragraph("Tipo de Producto").setBold());
        table.addHeaderCell(new Paragraph("Cantidad").setBold());
        table.addHeaderCell(new Paragraph("Tipo de Medidad").setBold());
        table.addHeaderCell(new Paragraph("Nombre Donante").setBold());
        table.addHeaderCell(new Paragraph("Telefono Donante").setBold());
        table.addHeaderCell(new Paragraph("Direccion Donante").setBold());

        // Obtener Donaciones y añadir sus datos
        List<Donacion> donaciones = this.service.ListarTodas();
        DecimalFormat df = new DecimalFormat("#,##0.00"); // Instanciamos el formateador

        for (Donacion donacion : donaciones) {
            table.addCell(donacion.getId().toString());
            table.addCell(donacion.getProducto());
            table.addCell(donacion.getTipo_producto());
            table.addCell(donacion.getCantidad().toString());
            table.addCell(donacion.getTipo_medida());
            table.addCell(donacion.getNombre_donante());
            table.addCell(donacion.getTelefono_donante());
            table.addCell(donacion.getDireccion_donante());

        }

        document.add(table);
        document.close();
    }

    @GetMapping("/reporte/excel")
    public void generarExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=donaciones_reporte.xlsx");

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Donaciones");

        Row headerRow = sheet.createRow(0);
        String[] columnHeaders = {"ID", "Producto", "Tipo de Producto", "Cantidad",
            "Tipo de Medidad","Nombre Donante", "Telefono Donante", "Direccion Donante"};
        for (int i = 0; i < columnHeaders.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnHeaders[i]);
            CellStyle style = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            style.setFont(font);
            cell.setCellStyle(style);
        }

        List<Donacion> donaciones = this.service.ListarTodas();
        int rowIndex = 1;
        for (Donacion donacion : donaciones) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(donacion.getId());
            row.createCell(1).setCellValue(donacion.getProducto());
            row.createCell(2).setCellValue(donacion.getTipo_producto());
            row.createCell(3).setCellValue(donacion.getCantidad());
            row.createCell(4).setCellValue(donacion.getTipo_medida());
            row.createCell(5).setCellValue(donacion.getNombre_donante());
            row.createCell(6).setCellValue(donacion.getTelefono_donante());
            row.createCell(7).setCellValue(donacion.getDireccion_donante());

        }

        /*for (int i = 0; columnHeaders.length; i++) {
            sheet.autoSizeColumn(i);
        }*/
        workbook.write(response.getOutputStream());
        workbook.close();

    }

}
