package com.cyrj.common.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;

import cn.hutool.core.util.ObjectUtil;
import net.coobird.thumbnailator.Thumbnails;

/**
 * 裁剪、缩放图片工具类
 */
public class ImgUtils {
	
	/**
	 * 缩放图片方法
	 * @param srcImageFile 要缩放的图片路径
	 * @param result 缩放后的图片路径
	 * @param bb     是否补白
	 */
	public final static void scale(String srcImageFile, String result, boolean bb) {
		try {
			double ratio = 0.0; // 缩放比例
//			File f = new File(srcImageFile);
			
			File tempFile = new File(System.getProperty("file.separator") + "temp");
	        if(!tempFile.exists())
	        {
	        	tempFile.mkdirs();
	        }
	        
	        // 获取文件后缀
	        String prefix=".jpg";
	        
	        // 用uuid作为文件名，防止生成的临时文件重复
	        File f = File.createTempFile(UUIDUtil.getNumUUID(3), prefix, tempFile);
			f = FileUtil.downLoadFromUrl(srcImageFile, f);
				
			BufferedImage bi = ImageIO.read(f);
			int width = bi.getWidth();
			int height = bi.getHeight();
			Image itemp = bi.getScaledInstance(width, height, bi.SCALE_SMOOTH);//bi.SCALE_SMOOTH  选择图像平滑度比缩放速度具有更高优先级的图像缩放算法。
			// 计算比例
			if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
				double   ratioHeight = (new Integer(height)).doubleValue()/ bi.getHeight();
				double   ratioWhidth = (new Integer(width)).doubleValue()/ bi.getWidth();
				if(ratioHeight>ratioWhidth){
					ratio= ratioHeight;
				}else{
					ratio= ratioWhidth;
				}
				AffineTransformOp op = new AffineTransformOp(AffineTransform//仿射转换
						.getScaleInstance(ratio, ratio), null);//返回表示剪切变换的变换
				itemp = op.filter(bi, null);//转换源 BufferedImage 并将结果存储在目标 BufferedImage 中。
			}
			if (bb) {//补白
				BufferedImage image = new BufferedImage(width, height,
						BufferedImage.TYPE_INT_RGB);//构造一个类型为预定义图像类型之一的 BufferedImage。
				Graphics2D g = image.createGraphics();//创建一个 Graphics2D，可以将它绘制到此 BufferedImage 中。
				g.setColor(Color.white);//控制颜色
				g.fillRect(0, 0, width, height);// 使用 Graphics2D 上下文的设置，填充 Shape 的内部区域。
				if (width == itemp.getWidth(null))
					g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2,
							itemp.getWidth(null), itemp.getHeight(null),
							Color.white, null);
				else
					g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0,
							itemp.getWidth(null), itemp.getHeight(null),
							Color.white, null);
				g.dispose();
				itemp = image;
			}
			ImageIO.write((BufferedImage) itemp, "JPEG", new File(result));      //输出压缩图片
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	/**
	 * 缩放图片方法
	 * @param srcImageFile 要缩放的图片路径
	 * @param result 缩放后的图片路径
	 * @param height 目标高度像素
	 * @param width  目标宽度像素  
	 * @param bb     是否补白
	 */
	public final static void scale(String srcImageFile, String result, int height, int width, boolean bb) {
		try {
			double ratio = 0.0; // 缩放比例
//			File f = new File(srcImageFile);
			
			File tempFile = new File(System.getProperty("file.separator") + "temp");
	        if(!tempFile.exists())
	        {
	        	tempFile.mkdirs();
	        }
	        
	        // 获取文件后缀
	        String prefix=".jpg";
	        
	        // 用uuid作为文件名，防止生成的临时文件重复
	        File f = File.createTempFile(UUIDUtil.getNumUUID(3), prefix, tempFile);
			f = FileUtil.downLoadFromUrl(srcImageFile, f);
				
			BufferedImage bi = ImageIO.read(f);
			Image itemp = bi.getScaledInstance(width, height, bi.SCALE_SMOOTH);//bi.SCALE_SMOOTH  选择图像平滑度比缩放速度具有更高优先级的图像缩放算法。
			// 计算比例
			if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
				double   ratioHeight = (new Integer(height)).doubleValue()/ bi.getHeight();
				double   ratioWhidth = (new Integer(width)).doubleValue()/ bi.getWidth();
				if(ratioHeight>ratioWhidth){
					ratio= ratioHeight;
				}else{
					ratio= ratioWhidth;
				}
				AffineTransformOp op = new AffineTransformOp(AffineTransform//仿射转换
						.getScaleInstance(ratio, ratio), null);//返回表示剪切变换的变换
				itemp = op.filter(bi, null);//转换源 BufferedImage 并将结果存储在目标 BufferedImage 中。
			}
			if (bb) {//补白
				BufferedImage image = new BufferedImage(width, height,
						BufferedImage.TYPE_INT_RGB);//构造一个类型为预定义图像类型之一的 BufferedImage。
				Graphics2D g = image.createGraphics();//创建一个 Graphics2D，可以将它绘制到此 BufferedImage 中。
				g.setColor(Color.white);//控制颜色
				g.fillRect(0, 0, width, height);// 使用 Graphics2D 上下文的设置，填充 Shape 的内部区域。
				if (width == itemp.getWidth(null))
					g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2,
							itemp.getWidth(null), itemp.getHeight(null),
							Color.white, null);
				else
					g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0,
							itemp.getWidth(null), itemp.getHeight(null),
							Color.white, null);
				g.dispose();
				itemp = image;
			}
			ImageIO.write((BufferedImage) itemp, "JPEG", new File(result));      //输出压缩图片
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	
	/**
	 * 缩放图片方法(不剪切)
	 * @param srcImageFile 要缩放的图片路径
	 * @param result 缩放后的图片路径
	 * @param height 目标高度像素
	 * @param width  目标宽度像素  
	 * @param bb     是否补白
	 */
	public final static void scaleNoCut(String srcImageFile, String result, int height, int width, boolean bb) {
		try {
			double ratio = 0.0; // 缩放比例
//			File f = new File(srcImageFile);
			
			File tempFile = new File(System.getProperty("file.separator") + "temp");
	        if(!tempFile.exists())
	        {
	        	tempFile.mkdirs();
	        }
	        
	        // 获取文件后缀
	        String prefix=".jpg";
	        
	        // 用uuid作为文件名，防止生成的临时文件重复
	        File f = File.createTempFile(UUIDUtil.getNumUUID(3), prefix, tempFile);
			f = FileUtil.downLoadFromUrl(srcImageFile, f);
				
			BufferedImage bi = ImageIO.read(f);
			Image itemp = bi.getScaledInstance(width, height, bi.SCALE_SMOOTH);//bi.SCALE_SMOOTH  选择图像平滑度比缩放速度具有更高优先级的图像缩放算法。
			if (bb) {//补白
				BufferedImage image = new BufferedImage(width, height,
						BufferedImage.TYPE_INT_RGB);//构造一个类型为预定义图像类型之一的 BufferedImage。
				Graphics2D g = image.createGraphics();//创建一个 Graphics2D，可以将它绘制到此 BufferedImage 中。
				g.setColor(Color.white);//控制颜色
				g.fillRect(0, 0, width, height);// 使用 Graphics2D 上下文的设置，填充 Shape 的内部区域。
				if (width == itemp.getWidth(null))
					g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2,
							itemp.getWidth(null), itemp.getHeight(null),
							Color.white, null);
				else
					g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0,
							itemp.getWidth(null), itemp.getHeight(null),
							Color.white, null);
				g.dispose();
				itemp = image;
			}
			ImageIO.write((BufferedImage) itemp, "JPEG", new File(result));      //输出压缩图片
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	

	
	/**
	 * 缩放图片方法
	 * @param srcImageFile 要缩放的图片路径
	 * @param result 缩放后的图片路径
	 * @param height 目标高度像素
	 * @param width  目标宽度像素  
	 * @param bb     是否补白
	 */
	public final static void scaleThumbnails(String srcImageFile, String result, int height, int width, boolean bb,String prefix) {
		try {
		
			File tempFile = new File(System.getProperty("file.separator") + "temp");
	        if(!tempFile.exists())
	        {
	        	tempFile.mkdirs();
	        }
	        
	        
	        // 用uuid作为文件名，防止生成的临时文件重复
	        File f = File.createTempFile(UUIDUtil.getNumUUID(3), prefix, tempFile);
			f = FileUtil.downLoadFromUrl(srcImageFile, f);
			if (f.exists()) {
				Thumbnails.of(f)
						.size(width, height)
                        .imageType(BufferedImage.TYPE_INT_ARGB)
						.outputFormat(prefix.replace(".", ""))
						.toFile(result);
			}
				
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println(e);
		}
	}

	/**
	 * 缩放图片方法二（图片大于设置压缩大小后进行压缩）
	 *
	 * @param srcImageFile 要缩放的图片路径
	 * @param result       缩放后的图片路径
	 * @param height       目标高度像素
	 * @param width        目标宽度像素
	 * @param prefix       图片后缀
	 * @param fileSize     图片超过指定大小后才可压缩（单位：mb）
	 */
	public final static Boolean scaleThumbnails2(String srcImageFile, String result, int height, int width,String prefix,int fileSize) {
		Boolean isSuc = true;
		try {

			File tempFile = new File(System.getProperty("file.separator") + "temp");
			if(!tempFile.exists())
			{
				tempFile.mkdirs();
			}


			// 用uuid作为文件名，防止生成的临时文件重复
			File f = File.createTempFile(UUIDUtil.getNumUUID(3), prefix, tempFile);
			f = FileUtil.downLoadFromUrl(srcImageFile, f);
			//要缩放的图片大小（单位：mb）
			double fSize = (double) f.length() / 1024 / 1024;
			if (ObjectUtil.isNotNull(fileSize) && fSize > fileSize){
				if (!f.exists()) {
					return false;
				}
				Thumbnails.of(f)
						.size(width, height)
                        .imageType(BufferedImage.TYPE_INT_ARGB)
						.outputFormat(prefix.replace(".", ""))
						.toFile(result);
			}else {
				isSuc = false;
			}

		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println(e);
		}
		return isSuc;
	}

	/**
	 * Thumbnails-图片文件指定大小压缩
	 *
	 * @param imgFile     压缩图片文件
	 * @param accuracy    压缩比
	 * @param appointSize 压缩指定大小（单位：KB）
	 * @return void
	 * @author cw
	 * @date 2022/11/3 8:42
	 */
	public static void commpressPicCycle(File imgFile, double accuracy, double appointSize) throws IOException {
		int KB = 1024;//定义KB的计算常量
		double fileSize = imgFile.length();
		// 格式化小数
		DecimalFormat df = new DecimalFormat("0.00");
		fileSize = Double.parseDouble(df.format(fileSize / (float) KB));
		//判断大小,如果小于压缩指定大小,不压缩,如果大于等于压缩指定大小,压缩
		if (fileSize <= appointSize) {
			return;
		}
		//计算宽高
		BufferedImage bim = ImageIO.read(imgFile);
		int imgWidth = bim.getWidth();
		int imgHeight = bim.getHeight();
		int desWidth = new BigDecimal(imgWidth).multiply(
				new BigDecimal(accuracy)).intValue();
		int desHeight = new BigDecimal(imgHeight).multiply(
				new BigDecimal(accuracy)).intValue();
		Thumbnails.of(imgFile).size(desWidth, desHeight).outputQuality(accuracy).toFile(imgFile);
		//如果不满足要求,递归直至满足小于压缩指定大小的要求
		commpressPicCycle(imgFile, accuracy, appointSize);
	}

	/**
	 * 裁剪图片方法
	 * @param bufferedImage 图像源
	 * @param startX 裁剪开始x坐标
	 * @param startY 裁剪开始y坐标
	 * @param endX 裁剪结束x坐标
	 * @param endY 裁剪结束y坐标
	 * @return
	 */
	public static BufferedImage cropImage(BufferedImage bufferedImage, int startX, int startY, int endX, int endY) {
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		if (startX == -1) {
			startX = 0;
		}
		if (startY == -1) {
			startY = 0;
		}
		if (endX == -1) {
			endX = width - 1;
		}
		if (endY == -1) {
			endY = height - 1;
		}
		BufferedImage result = new BufferedImage(endX - startX, endY - startY, 4);
		for (int x = startX; x < endX; ++x) {
			for (int y = startY; y < endY; ++y) {
				int rgb = bufferedImage.getRGB(x, y);
				result.setRGB(x - startX, y - startY, rgb);
			}
		}
		return result;
	}

	
}