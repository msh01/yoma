package com.github.yoma.base.modules.fastdfs;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.fdfs.ThumbImageConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.github.yoma.common.utils.StringUtils;

/**
 * Fastdfs工具类
 */
@Component
public class FastdfsService {

    private final Logger logger = LoggerFactory.getLogger(FastdfsService.class);
    @Autowired
    private FastFileStorageClient storageClient;
    @Autowired
    private ThumbImageConfig thumbImageConfig;


    // modified by msh 20200125 ,改为通过spring.profiles.include 方式加载yaml属性文件
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer properties() {
//        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
//        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
//        yaml.setResources(new ClassPathResource("fastdfs.yml"));
//        configurer.setProperties(yaml.getObject());
//        return configurer;
//    }

    // 上传文件
    public String uploadImage(MultipartFile myfile) throws Exception {
        // 文件名
        String originalFilename =
                myfile.getOriginalFilename().substring(myfile.getOriginalFilename().lastIndexOf(".") + 1);
        // 文件扩展名
        String ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1, originalFilename.length());

        StorePath storePath = this.storageClient.uploadImageAndCrtThumbImage(myfile.getInputStream(), myfile.getSize(),
                originalFilename, null);

        String path = storePath.getFullPath();

        return path;
    }

    public String uploadFile(MultipartFile myfile) throws Exception {
        // 文件名
        String originalFilename =
            myfile.getOriginalFilename().substring(myfile.getOriginalFilename().lastIndexOf(".") + 1);
        // 文件扩展名
        String ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1, originalFilename.length());

        StorePath storePath =
            this.storageClient.uploadFile(myfile.getInputStream(), myfile.getSize(), originalFilename, null);

        String path = storePath.getFullPath();

        return path;
    }
    // public String uploadFile(MultipartFile myfile) throws Exception {
    // // 文件名
    // String originalFilename =
    // myfile.getOriginalFilename().substring(myfile.getOriginalFilename().lastIndexOf(".") + 1);
    // // 文件扩展名
    // String ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1, originalFilename.length());
    //
    // StorePath storePath =
    // this.storageClient.uploadFile(myfile.getInputStream(), myfile.getSize(), originalFilename, null);
    //
    // String path = storePath.getFullPath();
    //
    // return path;
    // }

    /**
     * 删除文件
     *
     * @Param fileUrl 文件访问地址
     */
    public void deleteFile(String fileUrl) {
        if (StringUtils.isEmpty(fileUrl)) {
            return;
        }
        try {
            StorePath storePath = StorePath.parseFromUrl(fileUrl);
            storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
        } catch (FdfsUnsupportStorePathException e) {
            logger.warn(e.getMessage());
        }
    }
}
