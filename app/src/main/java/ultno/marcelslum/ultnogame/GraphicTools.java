package ultno.marcelslum.ultnogame;

import android.opengl.GLES20;

public class GraphicTools {

    // Program variables
    public static int sp_SolidColor;
    public static int sp_Image;
    public static int sp_Text;


    /* SHADER Solid
     *
     * This shader is for rendering a colored primitive.
     *
     */
    public static final String vs_SolidColor =
                    "uniform mat4 um4_projection;" +
                    "uniform mat4 um4_view;" +
                    "uniform mat4 um4_model;" +
                    "attribute vec4 av4_colors;" +
                    "varying vec4 vv4_Colors;" +
                    "attribute vec4 av4_vertices;" +
                    "varying float vf_alpha;" +
                    "   void main() {" +
                    "       gl_Position = um4_projection * um4_view * um4_model * av4_vertices;" +
                    "       vv4_Colors = av4_colors;" +
                    "   }";
    public static final String fs_SolidColor =
                    "precision mediump float;" +
                    "varying vec4 vv4_Colors;" +
                    "uniform float uf_alpha;" +
                    "void main() {" +
                    "  gl_FragColor = vec4(vv4_Colors.r, vv4_Colors.g, vv4_Colors.b, uf_alpha * vv4_Colors.a);" +
                    "}";

    public static final String vs_Line =
            "uniform mat4 um4_projection;" +
                    "uniform mat4 um4_view;" +
                    "uniform mat4 um4_model;" +
                    "attribute vec4 av4_colors;" +
                    "varying vec4 vv4_Colors;" +
                    "attribute vec4 av4_vertices;" +
                    "varying float vf_alpha;" +
                    "   void main() {" +
                    "       gl_Position = um4_projection * um4_view * um4_model * av4_vertices;" +
                    "       vv4_Colors = av4_colors;" +
                    "   }";
    public static final String fs_Line =
            "precision mediump float;" +
                    "varying vec4 vv4_Colors;" +
                    "uniform float uf_alpha;" +
                    "void main() {" +
                    "  gl_FragColor = vec4(vv4_Colors.r, vv4_Colors.g, vv4_Colors.b, uf_alpha * vv4_Colors.a);" +
                    "}";



    public static final String vs_Image =
        "uniform mat4 um4_projection;" +
        "uniform mat4 um4_view;" +
        "uniform mat4 um4_model;" +
        "uniform float uf_alpha;" +
        "attribute vec4 av4_vertices;" +
        "attribute vec2 av2_uv;" +
        "varying vec2 vv2_texCoord;" +
        "varying float vf_alpha;" +
        "   void main() {" +
        "       gl_Position = um4_projection * um4_view * um4_model * av4_vertices;" +
        "       vv2_texCoord = av2_uv;" +
        "  vf_alpha = uf_alpha;" +
        "   }";
    public static final String fs_Image =
        "precision mediump float;" +
        "varying vec2 vv2_texCoord;" +
        "varying float vf_alpha;" +
        "uniform sampler2D us_texture;" +
        "   void main() {" +
        "       vec4 color = texture2D( us_texture, vv2_texCoord);" +
        "       if (color.a < 0.01) discard;"+
        "       gl_FragColor = vec4(color.r, color.g, color.b, vf_alpha*color.a);"+

        "   }";


    public static final String vs_Image_Colorized =
            "uniform mat4 um4_projection;" +
                    "uniform mat4 um4_view;" +
                    "uniform mat4 um4_model;" +
                    "uniform float uf_alpha;" +
                    "attribute vec4 av4_vertices;" +
                    "attribute vec4 av4_colors;" +
                    "attribute vec2 av2_uv;" +
                    "varying vec4 vv4_Colors;" +
                    "varying vec2 vv2_texCoord;" +
                    "varying float vf_alpha;" +
                    "   void main() {" +
                    "       gl_Position = um4_projection * um4_view * um4_model * av4_vertices;" +
                    "       vv2_texCoord = av2_uv;" +
                    "       vv4_Colors = av4_colors;" +
                    "   }";
    public static final String fs_Image_Colorized =

            "precision mediump float;" +
                    "uniform float uf_alpha;" +
                    "varying vec2 vv2_texCoord;" +
                    "varying vec4 vv4_Colors;" +
                    "uniform sampler2D us_texture;" +
                    "   void main() {" +
                    "       vec4 color = texture2D( us_texture, vv2_texCoord);" +
                    "       if (color.a < 0.01) discard;"+
                    "       gl_FragColor = vec4(color.r + vv4_Colors.r, color.g + vv4_Colors.g, color.b + vv4_Colors.b, uf_alpha * vv4_Colors.a * color.a);"+
                    "   }";

    public static final String vs_Image_Colorized_fx =
            "uniform mat4 um4_projection;" +
                    "uniform mat4 um4_view;" +
                    "uniform mat4 um4_model;" +
                    "uniform float uf_alpha;" +
                    "uniform float uf_time;" +
                    "attribute vec4 av4_vertices;" +
                    "attribute vec4 av4_colors;" +
                    "attribute vec2 av2_uv;" +
                    "varying vec4 vv4_Colors;" +
                    "varying vec2 vv2_texCoord;" +
                    "varying float vf_alpha;" +
                    "   void main() {" +
                    "       gl_Position = um4_projection * um4_view * um4_model * av4_vertices;" +
                    "       gl_Position.x += uf_time;" +
                    "       gl_Position.y += uf_time;" +
                    "       vv2_texCoord = av2_uv;" +
                    "       vv4_Colors = av4_colors;" +
                    "   }";
    public static final String fs_Image_Colorized_fx =

            "precision mediump float;" +
                    "uniform float uf_alpha;" +
                    "uniform vec2 uv2_ballPosition;"+
                    "varying vec2 vv2_texCoord;" +
                    "varying vec4 vv4_Colors;" +
                    "uniform sampler2D us_texture;" +
                    "   void main() {" +
                    "       vec4 color = texture2D( us_texture, vv2_texCoord);" +
                    "       if (color.a < 0.01) discard;"+
                    "       float distancePoint = distance(gl_FragCoord.xy, uv2_ballPosition.xy);"+
                    "       if (distancePoint < 50.0){"+
                    "       vec2 randomSeed = vec2(gl_FragCoord.x/uv2_ballPosition.x, gl_FragCoord.y/uv2_ballPosition.y);"+
                    "       float invertedValue = fract(sin(dot(randomSeed.xy,vec2(12.9898,78.233))) * 43758.5453);"+
                    "       vec4 color2 = vec4((color.r + vv4_Colors.r)*invertedValue, color.g + vv4_Colors.g, color.b + vv4_Colors.b, uf_alpha * 0.8);"+
                    "       gl_FragColor = vec4(color2.r, color2.g, color2.b, color2.a);"+
                    "       } else {"+
                    "       gl_FragColor = vec4(color.r + vv4_Colors.r, color.g + vv4_Colors.g, color.b + vv4_Colors.b, uf_alpha * 0.8);"+
                    "       }"+
                    "   }";

    public static final String vs_Text =
        "uniform mat4 um4_projection;" +
        "uniform mat4 um4_view;" +
        "uniform mat4 um4_model;" +
        "uniform float uf_alpha;" +
        "attribute vec4 av4_vertices;" +
        "attribute vec2 av2_uv;" +
        "attribute vec4 av4_colors;" +
        "varying vec2 vv2_texCoord;" +
        "varying vec4 vv4_Colors;" +
        "varying float vf_alpha;" +
        "void main() {" +
        "  gl_Position = um4_projection * um4_view * um4_model * av4_vertices;" +
        "  vv2_texCoord = av2_uv;" +
        "  vv4_Colors = av4_colors;" +
        "  vf_alpha = uf_alpha;" +
        "}";
    public static final String fs_Text =
            "precision mediump float;" +
                    "varying vec2 vv2_texCoord;" +
                    "varying vec4 vv4_Colors;" +
                    "varying float vf_alpha;" +
                    "uniform sampler2D us_texture;" +
                    "void main() {" +
                    " gl_FragColor = texture2D( us_texture, vv2_texCoord );" +
                    " if (gl_FragColor.a < 0.01) discard;"+
                    " gl_FragColor = vec4(vv4_Colors.r, vv4_Colors.g, vv4_Colors.b, vf_alpha*vv4_Colors.a);" +
                    "}";



    /*
     public static final String fs_Image_Alpha =
    
                "precision mediump float;" +
                        "varying vec2 vv2_texCoord;" +
                        "varying float vf_alpha;" +
                        "uniform sampler2D us_texture;" +
                        "   void main() {" +
                        "       vec4 color = texture2D( us_texture, vv2_texCoord);" +
                        "       if (color.a < 0.01) discard;"+
                        "       gl_FragColor = vec4(color.rgb,vf_alpha*color.a);"+
    
                        "   }";


      public static final String vs_Image_Alpha_Array =

            "uniform mat4 um4_projection;" +
            "uniform mat4 um4_view;" +
            "uniform mat4 um4_model;" +
            "uniform float uf_alpha;" +
            "attribute vec4 av4_vertices;" +
            "attribute float af_alphaArray;" +
            "attribute vec2 av2_uv;" +
            "varying vec2 vv2_texCoord;" +
            "varying float vf_alphaArray;" +
            "varying float vf_alpha;" +
            "   void main() {" +
            "       gl_Position = um4_projection * um4_view * um4_model * av4_vertices;" +
            "       vv2_texCoord = av2_uv;" +
            "       vf_alphaArray = af_alphaArray;" +
            "       vf_alpha = uf_alpha;" +
            "   }";
    public static final String fs_Image_Alpha_Array =
        "precision mediump float;" +
        "varying vec2 vv2_texCoord;" +
        "varying float vf_alphaArray;" +
        "varying float vf_alpha;" +
        "uniform sampler2D us_texture;" +
        "   void main() {" +
        "       vec4 color = texture2D( us_texture, vv2_texCoord);" +
        "       if (color.a < 0.01) discard;"+
        "       gl_FragColor = vec4(color.r, color.g, color.b, vf_alpha * color.a * vf_alphaArray);"+

        "   }";

            public static final String vs_SolidColor_AlphaArray =
            "uniform mat4 um4_projection;" +
                    "uniform mat4 um4_view;" +
                    "uniform mat4 um4_model;" +
                    "uniform float uf_alpha;" +
                    "attribute vec4 av4_colors;" +
                    "attribute float af_alphaArray;" +
                    "varying vec4 vv4_Colors;" +
                    "attribute vec4 av4_vertices;" +
                    "varying float vf_alphaArray;" +
                    "varying float vf_alpha;" +
                    "   void main() {" +
                    "       gl_Position = um4_projection * um4_view * um4_model * av4_vertices;" +
                    "       vv4_Colors = av4_colors;" +
                    "       vf_alphaArray = af_alphaArray;" +
                    "       vf_alpha = uf_alpha;" +
                    "   }";
    public static final String fs_SolidColor_AlphaArray =
            "precision mediump float;" +
                    "varying vec4 vv4_Colors;" +
                    "varying float vf_alpha;" +
                    "varying float vf_alphaArray;"+
                    "void main() {" +
                    "  gl_FragColor = vec4(vv4_Colors.r, vv4_Colors.g, vv4_Colors.b, vf_alpha * vf_alphaArray);" +
                    "}";



 
    */
}
