package com.sainnt.netshop.jwt.spring

import com.sainnt.netshop.jwt.utils.JwtService
import com.sainnt.netshop.jwt.utils.JwtService.Companion.ROLES_KEY
import com.sainnt.netshop.jwt.utils.RsaUtils
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.mock
import java.security.PrivateKey
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

internal class JwtAuthenticationFilterTest {

    @Test
    fun doFilterInternal() {
//        RsaUtils.generateKey("shop.pub","shop","sfkdmslfmsl",2048)
//        val filter = JwtAuthenticationFilter(JwtService(RsaUtils.getPublicKey("shop.pub")))
        val filter = JwtAuthenticationFilter(JwtService(SAMPLE_PUBLIC_KEY))
//        val token = generateToken(1, listOf("ADMIN"), privateKey = RsaUtils.getPrivateKey("shop"))
        val token = generateToken("somelonguseris", listOf("ADMIN"), privateKey = RsaUtils.getPrivateKey(SAMPLE_PRIVATE_KEY.toByteArray()))
        val httpRequest: HttpServletRequest = mock(){
            on(it.getHeader("Authorization")).thenReturn("Bearer $token")
        }
        println("Jwt  $token")
        filter.parseRequestTokenData(httpRequest)
    }

    private fun generateToken(userId: String, roles:List<String>, privateKey: PrivateKey, expiration: Long = 10000): String {
        val date = Date()
        return Jwts.builder()
            .setClaims(mutableMapOf<String,Any>(ROLES_KEY to roles))
            .setSubject(userId)
            .setIssuedAt(date)
            .setExpiration(Date(date.time + expiration))
            .signWith(SignatureAlgorithm.RS256, privateKey)
            .compact()
    }


    companion object{
        const val SAMPLE_PRIVATE_KEY = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCN3UHUvjcUMS3np1qTzna4WHoFR2fTDExlNur0b7M8eiMH5wemnX/OGyT0fFdlUc82UAcJL6i18eDdtJoBkiIUvtbXlHxC2WVCFz3f7yQqdb0Q3blXfjNLAd3JyiUnx1ZRgFbfQhRGdR03k3srX7gjQkSu0F2cwUMWQhXTp73CVBp7HDCYpWJstiVAAaHzeNZ6hChb7q9p6j6eJKhSv1tFWDgbf3t3PV6l2f4PVtffE1G/r9l3Lf4vhZPuM0fCLiZZ+Hr6j5HEOyIsDpqE0tC/mqqYZ2AQ9RjKgFssWWFfV1iRTJ+Jp3iitkm3iUAtvxCoU2wFfW50/eMG7+5yaOi3AgMBAAECggEBAIzu+R5zld5OeS9A9+AM5a5/30DtTjzBZAfNRh9t9J8pKzIHl3cz0qscFlt4R46VdoibelHq8e1HDUDT0Yer+IWpjV//2G7E8C53TnXwanpwG1LtItrDugtTIFX+p2jcRGmVWThUujXA1TTIJD3TyufkRr1wrP80lARBxNNPOloZjwzp/zF2pLnUb5ygEcSZKKMluoZmVoZ/00WLY1AnOSgTPNS5k8ZAqgGacep94zvKZS6aOow22N6N64nDeIv93qQdO85j9KOiJgACXM2lJkaz3anTNsiF/XefXTvW/cLPbbXuP2zY1WTV/u8W0dqOX0zawWLm9YW9BArisPcgaYECgYEA/gVI/JiSLkzJhOy8nTvyDymeUj0AmTT8VOVqFTDSnHu5DwIRPlvRyLLcxgoNoQA7A9TElDasDfFou/iZ1+8A81EYqYG5K2Zioaew9zB/L0WuLm591sVDahCDzZC7bVoZ04bLnhF7MhTIeA0RK1I16U3mJfCHmqSP6n4WbQK2/MECgYEAjvg+tg2N9KQQ563IA9hl8AvYwYdLHSzE4zNuidrWFH7zosFWNFTicd9DtqKG6YmSx2+PIrQrXhXIeHnoJWoEj0yBRx9etcNOvgdJrA7yaaOABAbl+7u6p8W4YoQzfQH/5kIPJU8jQDCYBR2aRDN/VQnCCOG+p+8y/uyoBqslK3cCgYAvzHbLcTowlCpnZNSpEZqe18n+15PBQIMqxnCLxEAdSfqspEryXPzAs0dvzHx8CzsPoX3MFe2SQhbfKAxq4YJgRqZJXyukywNMP+3A1dNY5de8zPXI8B2bW7gxekaKcSXi875k5A02g0qlrG/KoxGZ8RqZkQUzSDv8d/VoVdZxAQKBgQCK6X8dKmxcYDEgukRWICNjqk8QMuFz1nTzAJ0NirhpL7SP1eRpcnSXCcRkJuSI8yfPaYCcyow8nVOsY16BQ9JsXXxa24sNdlgC4VfeuqMkqz0OLJm3Dv9cVgtChYr1kkTOuC65JHxuuQhsKAgk7hhxSsriEF1MFGjrMoxL9mxmHwKBgQC6yiE1qZ4sLbTqJMNNtF7T4WvDpCLLGnQpSf7ACik6gczysHR+MFyRiwzxGzL1BiUORmDENZSAhj/p0O1amSYGrDRjdHVE2vWwrsCpkSrGtFZ/HAe/nHgqj1EIdojzn3X4Eez1wYPp67EsIzVbcckjIvo5gy5W4JONvNPVYpdVHg=="
        const val SAMPLE_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjd1B1L43FDEt56dak852uFh6BUdn0wxMZTbq9G+zPHojB+cHpp1/zhsk9HxXZVHPNlAHCS+otfHg3bSaAZIiFL7W15R8QtllQhc93+8kKnW9EN25V34zSwHdycolJ8dWUYBW30IURnUdN5N7K1+4I0JErtBdnMFDFkIV06e9wlQaexwwmKVibLYlQAGh83jWeoQoW+6vaeo+niSoUr9bRVg4G397dz1epdn+D1bX3xNRv6/Zdy3+L4WT7jNHwi4mWfh6+o+RxDsiLA6ahNLQv5qqmGdgEPUYyoBbLFlhX1dYkUyfiad4orZJt4lALb8QqFNsBX1udP3jBu/ucmjotwIDAQAB"
//        const val SAMPLE_PUBLIC_KEY  = "TUlJQ0lqQU5CZ2txaGtpRzl3MEJBUUVGQUFPQ0FnOEFNSUlDQ2dLQ0FnRUFuMmFvQnhHOWtqRERyN0dXeHlWMApSWnhrU2krMFhsclozK3BRbWEvekY3VjFlSjZuaEVxckkwaUNjSEp2SkpZMTM2UENyYUdia29hVi9BRTdiVllXCkE1UXJRM3UvSTBVY1Q4YWgveUhDa2g3UnYwTXFTQzBVbEw3QjdVeE1McHRrdkpBdEE4RW5pZWRMRGlrVGRPWHMKLzRwUWQyTFdGZkFOZmJrNU54aSsvTGVobjloNXF2TSsyVklvL3NBcy9rczJlcTlvelRkWnR4ajhuakg3RFgySgoyUU5FazIxQThZSTRaK1JzenBMSU5WeDhPQ0RMc2JnVjN3NXVYMWNOUUsrR3lkaTM3MGhhNmllMFZRMGdGUUE5ClBqU3FVN09qNWdKVm5GdnNKRUsrNlRwSWpaSTgyNEdacmVZbnV4SUJXaWF3M09jdEZaWlB3eEhTYURXZWkvYSsKMHpVYUFEeGFEUHF4MzArOTNQZzZTekJIUWFPYzBFWnFZMWROb24xZ09SQ2p5aUJFb0J2czVCQVR1YkJiSW5zVwo1WkwxZU1JVGpHTnhkclZPS2VxWWZ2b3dnYzZjL1p2amM2WFVaQlNHRmdseGNiTDZxV0EvYmVUNjZ5SnFlSnp4ClE4bE42NG1sc1ppS3J2YzJsZ3MyNDd4dkJ4dHJsUmFZWDJjNVFMQVk5RGpqYyt6dmQzV3FNM0VBNkxtdVVQdDUKWmdCSExCUjc3UENaZjR4dU1Wc2grWkpFMUJHbXlrL1RNaFk3eWRncXJPQmxTam5PYXQyYnBNOUFCR3RwaGhXMgpYOU5kRWIraGYxb1VFa1d4MWE3c2I1bktpb3JPb3VHczFwUnkwamZJeXUwQ1BNRWgybkFlSmxUWisxajZxelBFCml1WVpNYlByOGRHUnRmMkNBRys1M0ZFQ0F3RUFBUT09"
    }

}