package com.multi.travel.common;

import com.multi.travel.common.CommonConst;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
 
import javax.imageio.ImageIO;
 
public class ImageResize {
 

    	
    	
    public static void ImageResize(String ImagePath, String name) {
    	
    	  
    	
        String imgOriginalPath= ImagePath+"/"+name;           // ���� �̹��� ���ϸ�
        String imgTargetPath= "C:/test/test_resize.jpg";    // �� �̹��� ���ϸ�
        String imgFormat = "jpg";                             // �� �̹��� ����. jpg, gif ��
        int newWidth = 700;                                  // ���� �� ����
        int newHeight = 617;                                 // ���� �� ����
        String mainPosition = "W";                             // W:�����߽�, H:�����߽�, X:������ ��ġ��(��������)
 
        Image image;
        int imageWidth;
        int imageHeight;
        double ratio;
        int w;
        int h;
 
        try{
            // ���� �̹��� ��������
            image = ImageIO.read(new File(imgOriginalPath));
 
            // ���� �̹��� ������ ��������
            imageWidth = image.getWidth(null);
            imageHeight = image.getHeight(null);
 
            if(mainPosition.equals("W")){    // ���̱���
 
                ratio = (double)newWidth/(double)imageWidth;
                w = (int)(imageWidth * ratio);
                h = (int)(imageHeight * ratio);
 
            }else if(mainPosition.equals("H")){ // ���̱���
 
                ratio = (double)newHeight/(double)imageHeight;
                w = (int)(imageWidth * ratio);
                h = (int)(imageHeight * ratio);
 
            }else{ //������ (��������)
 
                w = newWidth;
                h = newHeight;
            }
 
            // �̹��� ��������
            // Image.SCALE_DEFAULT : �⺻ �̹��� �����ϸ� �˰��� ���
            // Image.SCALE_FAST    : �̹��� �ε巯�򺸴� �ӵ� �켱
            // Image.SCALE_REPLICATE : ReplicateScaleFilter Ŭ������ ��üȭ �� �̹��� ũ�� ���� �˰���
            // Image.SCALE_SMOOTH  : �ӵ����� �̹��� �ε巯���� �켱
            // Image.SCALE_AREA_AVERAGING  : ��� �˰��� ���
            Image resizeImage = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
 
            // �� �̹���  �����ϱ�
            BufferedImage newImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            Graphics g = newImage.getGraphics();
            g.drawImage(resizeImage, 0, 0, null);
            g.dispose();
            ImageIO.write(newImage, imgFormat, new File(imgTargetPath));
 
        }catch (Exception e){
 
            e.printStackTrace();
 
        }
    }
 
    
 
}