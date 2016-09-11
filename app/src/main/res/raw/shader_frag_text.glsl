precision mediump float;
varying vec2 vv2_texCoord;
varying vec4 vv4_Colors;
varying float vf_alpha;
uniform sampler2D us_texture;
void main() {
    gl_FragColor = texture2D( us_texture, vv2_texCoord );
    if (gl_FragColor.a < 0.01) discard;
    gl_FragColor = vec4(vv4_Colors.r, vv4_Colors.g, vv4_Colors.b, vf_alpha*vv4_Colors.a);
}
