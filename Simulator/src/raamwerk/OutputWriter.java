/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raamwerk;

import java.io.*;
import java.util.*;

/**
 *
 * @author Jasper De Vrient
 */
public interface OutputWriter {
      void setOutput(File file);
    
     void openOutput(Simulatie simulatie, List<UitvoerVelden> velden);
    
     void writeEntry(Map<UitvoerVelden, Object> entry);
    
     void closeOutput(Simulatie simulatie);
     
     default String extention() {
         return "";
     }
}
