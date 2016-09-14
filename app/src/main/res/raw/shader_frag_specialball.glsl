precision mediump float;
varying vec2 vv2_texCoord;
varying float vf_alpha;
uniform sampler2D us_texture;
varying vec4 vv4_Colors;
uniform vec2 uv2_resolution;
uniform float uf_time;
void main() {
   vec4 color = texture2D( us_texture, vv2_texCoord);
    if (color.a < 0.01){
        discard;
    }
    float frequency = 600.0;
    float angle = mod(uf_time, 360.0/frequency);
    float varCos = abs(cos(radians(angle*frequency)));

    if (color.b >  0.35){
        gl_FragColor = vec4(color.r+(0.6*varCos), color.g+(0.1*varCos), color.b+(0.2*varCos), vf_alpha*color.a);
    } else {
        gl_FragColor = vec4(color.r, color.g, color.b, vf_alpha*color.a);
    }

}