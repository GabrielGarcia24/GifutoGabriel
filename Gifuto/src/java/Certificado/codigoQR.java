package Certificado;

import com.barcodelib.barcode.QRCode;
import java.io.File;

public class codigoQR {

    static void eliminaQR(String[] infoLiquidacion) {
        //Cambiar la ruta del proyecto
        File f = new File("C:\\CertificadosGifuto\\QR\\" + infoLiquidacion[6] + "_" + infoLiquidacion[5] + ".png");
        f.delete();
    }

    static void generarQR(String[] infoLiquidacion) {

        int udm = 0;
        int resolucion = 72;
        float margenIzq = 0.000f;
        float margenDer = 0.000f;
        float margenSup = 0.000f;
        float margenInf = 0.000f;
        int rotacion = 0;
        float tamanoModulo = 4.000f;

        QRCode codigoQR = new QRCode();

        codigoQR.setData("total "+infoLiquidacion[0] + " " + infoLiquidacion[1] + " "
                + infoLiquidacion[2] + " " + infoLiquidacion[3] + " "
                + infoLiquidacion[4] + " " + infoLiquidacion[5] + " "
                + infoLiquidacion[6] + " " + infoLiquidacion[7] + " "
                + infoLiquidacion[8] + " " + infoLiquidacion[9] + " "
                + infoLiquidacion[10]);

        codigoQR.setDataMode(QRCode.MODE_BYTE);

        codigoQR.setUOM(udm);
        codigoQR.setLeftMargin(margenIzq);
        codigoQR.setResolution(resolucion);
        codigoQR.setRightMargin(margenDer);
        codigoQR.setTopMargin(margenSup);
        codigoQR.setBottomMargin(margenInf);
        codigoQR.setRotate(rotacion);
        codigoQR.setModuleSize(tamanoModulo);

        String archivo = "C:\\CertificadosGifuto\\QR\\" + infoLiquidacion[6] + "_" + infoLiquidacion[5] + ".png";
        try {
            codigoQR.renderBarcode(archivo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
