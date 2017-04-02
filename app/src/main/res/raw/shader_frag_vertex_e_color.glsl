precision mediump float;
uniform float uf_alpha;
varying vec4 vv4_color;
void main() {
    gl_FragColor = vec4(0.8, 0.6, 0.4, 1.0);
    //gl_FragColor = vec4(vv4_color.r, vv4_color.g, vv4_color.b, vv4_color.a * uf_alpha);
}
