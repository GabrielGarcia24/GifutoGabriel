/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventoEspecial;

import com.barcodelib.barcode.QRCode;
import java.io.File;

/**
 *
 * @author Gabriel
 */
public class QRSRE {
    
 public static void eliminaQR(String[] info) {
        //Cambiar la ruta del proyecto
        File f = new File("C:\\SRE\\QR\\no_Servicio"+info[0]+".png");
        f.delete();
    }

    public static void generarQR(String[] info) {

        int udm = 0;
        int resolucion = 72;
        float margenIzq = 0.000f;
        float margenDer = 0.000f;
        float margenSup = 0.000f;
        float margenInf = 0.000f;
        int rotacion = 0;
        float tamanoModulo = 2.000f;

        QRCode codigoQR = new QRCode();

        codigoQR.setData(info[0] + " " + info[1] + " "
                + info[2] + " " + info[3] + " "
                + info[4] + " " + info[5] + " "
                + info[6] + " " + info[7] + " "
                + info[8] + " " + info[9] + " "
                + info[10]+ " "
                + info[11]);

        codigoQR.setDataMode(QRCode.MODE_BYTE);

        codigoQR.setUOM(udm);
        codigoQR.setLeftMargin(margenIzq);
        codigoQR.setResolution(resolucion);
        codigoQR.setRightMargin(margenDer);
        codigoQR.setTopMargin(margenSup);
        codigoQR.setBottomMargin(margenInf);
        codigoQR.setRotate(rotacion);
        codigoQR.setModuleSize(tamanoModulo);

        String archivo = "C:\\SRE\\QR\\no_Servicio"+info[0]+".png";
        try {
            codigoQR.renderBarcode(archivo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
