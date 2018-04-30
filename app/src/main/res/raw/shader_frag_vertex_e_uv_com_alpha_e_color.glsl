precision mediump float;
varying float alpha;
uniform float uf_alpha;
varying vec2 vv2_texCoord;
varying vec4 vv4_Colors;
uniform sampler2D us_texture;
void main() {
   vec4 color = texture2D( us_texture, vv2_texCoord);
   if (color.a < 0.001) discard;
   gl_FragColor = vec4((color.r * 0.3f) + (vv4_Colors.r * 0.7f), (color.g  * 0.3f) + (vv4_Colors.g * 0.7f), (color.b  * 0.3f) + ( vv4_Colors.b * 0.7f), color.a * alpha * uf_alpha * vv4_Colors.a);
}
