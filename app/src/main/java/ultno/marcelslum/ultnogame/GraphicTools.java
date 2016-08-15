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
                    "uniform float uf_alpha;" +
                    "attribute vec4 av4_colors;" +
                    "varying vec4 vv4_Colors;" +
                    "attribute vec4 av4_vertices;" +
                    "varying float vf_alpha;" +
                    "   void main() {" +
                    "       gl_Position = um4_projection * um4_view * um4_model * av4_vertices;" +
                    "       vv4_Colors = av4_colors;" +
                    "       vf_alpha = uf_alpha;" +
                    "   }";


    public static final String fs_SolidColor =
                    "precision mediump float;" +
                    "varying vec4 vv4_Colors;" +
                    "varying float vf_alpha;" +
                    "void main() {" +
                    "  gl_FragColor = vec4(vv4_Colors.r, vv4_Colors.g, vv4_Colors.b, vf_alpha);" +
                    "}";

    /* SHADER Image
     *
     * This shader is for rendering 2D images straight from a texture
     * No additional effects.
     *
     */
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
        "       gl_FragColor = vec4(color.rgb,vf_alpha);"+

        "   }";

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




        String outro =         "       gl_FragColor = vec4(gl_FragColor.rgb,vf_alpha);" +
        "       vec4 color = texture2D( us_texture, vv2_texCoord);" +

        "       gl_FragColor = vec4(color.rgb,vf_alpha);" +


            "       if (color.w < 1) gl_FragColor = vec4(1,0,0,1);" +
            "       else gl_FragColor = vec4(1,1,0,1);";
    //texture2D( us_texture, vv2_texCoord );
    /* SHADER Text
     *
     * This shader is for rendering 2D text textures straight from a texture
     * Color and alpha blended.
     *
     */
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
                    " if (gl_FragColor.rgb == vec3(0.0,0.0,0.0))"+
                    " discard;"+
                    " gl_FragColor = vec4(vv4_Colors.rgb,vf_alpha*vv4_Colors.a);" +
                    "}";




}