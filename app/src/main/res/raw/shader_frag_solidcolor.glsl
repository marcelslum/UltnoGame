precision mediump float;
varying vec4 vv4_Colors;
uniform float uf_alpha;
void main() {
  gl_FragColor = vec4(vv4_Colors.r, vv4_Colors.g, vv4_Colors.b, uf_alpha * vv4_Colors.a);
}