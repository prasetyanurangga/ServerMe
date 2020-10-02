package com.prasetyanurangga.ServerMe.controller

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


@RestController
@RequestMapping("/file")
class FileController {

    @RequestMapping(value = ["/upload_file"], method = arrayOf(RequestMethod.POST), consumes = arrayOf(MediaType.MULTIPART_FORM_DATA_VALUE))
    @Throws(IOException::class)
    fun fileUpload(@RequestParam("file") file: MultipartFile): String? {
        val convertFile = File("E:/my_lab/" + file.originalFilename)
        convertFile.createNewFile()
        val fout = FileOutputStream(convertFile)
        fout.write(file.bytes)
        fout.close()
        return "File is upload successfully"
    }

}