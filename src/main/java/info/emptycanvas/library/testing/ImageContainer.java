package info.emptycanvas.library.testing;


import java.awt.image.BufferedImage;

public class ImageContainer {

        private BufferedImage biic;
        private String str = "";

        public BufferedImage getImage() {
            return biic;
        }

        public String getStr() {
            return str;
        }

        public void setImage(BufferedImage biic1) {
            biic = biic1;
        }

        public void setStr(String str) {
            this.str = str;
        }
    }