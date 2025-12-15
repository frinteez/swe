package artcreator.creator.port;

public interface Creator {
	void selectImage(String imagePath);
	void setRasterParameters(String rasterParams);
	void setMaterialProfile(String materialProfile);
	void generateTemplate();
	void acceptTemplate();
}

