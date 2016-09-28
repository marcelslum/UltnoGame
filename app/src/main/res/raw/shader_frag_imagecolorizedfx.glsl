precision mediump float;
uniform float uf_alpha;
varying vec2 vv2_texCoord;
varying vec4 vv4_Colors;
uniform sampler2D us_texture;
void main() {
    vec4 color = texture2D( us_texture, vv2_texCoord);
    gl_FragColor = vec4(color.r + vv4_Colors.r, color.g + vv4_Colors.g, color.b + vv4_Colors.b, uf_alpha);
}
