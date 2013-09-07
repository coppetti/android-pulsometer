package com.matheus.pulsometro;

public abstract class Fotopletismografia {

    private static int sumRed(byte[] videoSample, int width, int height) {
        int max = 262143;
        int min = 0;
    	if (videoSample == null) return 0;

        final int frameSize = width * height;

        int sum = 0;
        for (int j = 0, yPoints = 0; j < height; j++) {
            int uvPoints = frameSize + (j >> 1) * width, u = 0, v = 0;
            for (int i = 0; i < width; i++, yPoints++) {
                int y = (0xff & ((int) videoSample[yPoints])) - 16;
                if (y < 0) y = 0;
                if ((i & 1) == 0) {
                    v = (0xff & videoSample[uvPoints++]) - 128;
                    u = (0xff & videoSample[uvPoints++]) - 128;
                }
                
                int r = ((1192 * y) + 1634 * v);
                int g = ((1192 * y) - 833 * v - 400 * u);
                int b = ((1192 * y) + 2066 * u);
                
                if (r < min){
                	r = min;
                }
                else if (r > max){
                	r = max;
                }
                if (g < min){
                	g = min;
                }
                else if (g > max){
                	g = max;
                }
                if (b < min){
                	b = min;
                }
                else if (b > max){
                	b = max;
                }

                int pixelColor = 0xff000000 | ((r << 6) & 0xff0000) | ((g >> 2) & 0xff00) | ((b >> 10) & 0xff);
                
                sum += (pixelColor >> 16) & 0xff;
            }
        }
        return sum;
    }

    /**
     * Given a byte array representing a videoSample image, determine the average
     * amount of red in the image. Note: returns 0 if the byte array is NULL.
     * 
     * @param videoSample
     *            Byte array representing a videoSample image
     * @param width
     *            Width of the image.
     * @param height
     *            Height of the image.
     * @return int representing the average amount of red in the image.
     */
    public static int redAVG(byte[] videoSample, int width, int height) {
    	if (videoSample != null){ 
        	return (sumRed(videoSample, width, height) / (width * height));
        }
        else{
        	return 0;
        }
    }
}
