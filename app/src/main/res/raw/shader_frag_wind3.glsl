precision mediump float;
varying vec4 vv4_Colors;
uniform float uf_alpha;
uniform vec2 uv2_resolution;
uniform float uf_time;
void main() {

    float espessura = 0.05;

    vec2 relativePosition = gl_FragCoord.xy/uv2_resolution.xy;
    float frequency = vv4_Colors.b;

    // resto da divisão entre o tempo e a frequencia
    float resto = mod(uf_time, frequency);
    // porcentagem do resto no eixo x
    float porcentagem = resto/frequency;
    float x = uv2_resolution.x * (porcentagem);
    // diferença entre o fragmento atual e o ponto x utilizado como base
    float fragmentRelativePosition =((gl_FragCoord.x-x)/uv2_resolution.x);

    // centralY
    float y = 1.0-vv4_Colors.a + (espessura * 0.25 * cos(radians(fragmentRelativePosition*360.0)));

    float alpha = 0.2;

    if (relativePosition.y > (y-espessura) && relativePosition.y < (y+espessura)){
        if (relativePosition.y < y){
            alpha *= 1.0-((y - relativePosition.y)/espessura);
        } else {
            alpha *= 1.0-(relativePosition.y - y)/espessura;
        }
    } else {
        alpha = 0.0;
    }

    float initX = vv4_Colors.r - 0.5 + porcentagem;
    float finalX = vv4_Colors.g - 0.5 + porcentagem;
    float sizeX = finalX - initX;
    float centerX = initX + (sizeX*0.5);

    float preAlpha = 1.0;
    if (!(vv4_Colors.r == 0.0 && vv4_Colors.g == 1.0)){
        if (relativePosition.x > initX && relativePosition.x < finalX){
           if (relativePosition.x > centerX){
                preAlpha = ((finalX - relativePosition.x)/(sizeX*0.5));
            } else {
                preAlpha = ((relativePosition.x - initX)/(sizeX*0.5));
            }


            if ((porcentagem)< 0.5){
                alpha *= preAlpha * porcentagem*2.0;
            } else {
                alpha *= preAlpha * (1.0 - ((porcentagem-0.5)*2.0));
            }
        } else {
            alpha = 0.0;
        }
    } else {
        alpha *= 0.5;
    }


    gl_FragColor = vec4(1.0, 1.0, 1.0, alpha*1.2*uf_alpha);
}